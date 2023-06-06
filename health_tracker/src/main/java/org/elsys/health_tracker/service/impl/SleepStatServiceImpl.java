package org.elsys.health_tracker.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.elsys.health_tracker.controller.resources.SleepStatResource;
import org.elsys.health_tracker.controller.resources.UserSleepStatResource;
import org.elsys.health_tracker.entity.QualityStatus;
import org.elsys.health_tracker.entity.SleepStat;
import org.elsys.health_tracker.mapper.DateMapper;
import org.elsys.health_tracker.repository.SleepStatRepository;
import org.elsys.health_tracker.service.SleepStatService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.elsys.health_tracker.mapper.SleepStatMapper.SLEEP_STAT_MAPPER;

@Service
@RequiredArgsConstructor
public class SleepStatServiceImpl implements SleepStatService {
    private final SleepStatRepository sleepStatRepository;

    @Override
    public List<SleepStatResource> getAll() {
        return SLEEP_STAT_MAPPER.toSleepStatResources(sleepStatRepository.findAll());
    }

    @Override
    public Optional<SleepStatResource> getById(Long id) {
        return sleepStatRepository.findById(id).map(SLEEP_STAT_MAPPER::toSleepStatResource);
    }

    @Override
    public List<QualityStatus> getAllQualityStatuses() {
        return List.of(QualityStatus.values());
    }

    @Override
    public Optional<List<UserSleepStatResource>> getAllByUserId(Long userId) {
        return sleepStatRepository.findAllByUserId(userId).map(SLEEP_STAT_MAPPER::toUserSleepStatResources);
    }

    @Override
    public SleepStatResource create(SleepStatResource sleepStatResource) {
        try {
            SleepStat sleepStat = sleepStatRepository.save(SLEEP_STAT_MAPPER.fromSleepStatResource(sleepStatResource));
            sleepStatResource.setId(sleepStat.getId());
            return sleepStatResource;
        } catch (DataIntegrityViolationException e) {
            throw new EntityNotFoundException("Foreign key constraint violation.");
        }
    }

    @Override
    public SleepStatResource update(SleepStatResource sleepStatResource, Long id) {
        try {
            SleepStat sleepStat = sleepStatRepository.getReferenceById(id);

            sleepStat.setDuration(sleepStatResource.getDuration());
            sleepStat.setQualityStatus(sleepStatResource.getQualityStatus());
            sleepStat.setDate(DateMapper.toSQLDate(sleepStatResource.getDate()));

            return SLEEP_STAT_MAPPER.toSleepStatResource(sleepStatRepository.save(sleepStat));
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Unable to find sleep stat with id " + id + ".");
        }
    }

    @Override
    public void delete(Long id) {
        if (sleepStatRepository.existsById(id)) {
            sleepStatRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Unable to find sleep stat with id " + id + ".");
        }
    }
}
