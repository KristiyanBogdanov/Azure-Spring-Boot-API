package org.elsys.health_tracker.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.elsys.health_tracker.controller.resources.SleepStatResource;
import org.elsys.health_tracker.controller.resources.UserSleepStatResource;
import org.elsys.health_tracker.entity.QualityStatus;
import org.elsys.health_tracker.entity.SleepStat;
import org.elsys.health_tracker.exception.DuplicateEntityFieldException;
import org.elsys.health_tracker.mapper.DateMapper;
import org.elsys.health_tracker.repository.SleepStatRepository;
import org.elsys.health_tracker.service.SleepStatService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public SleepStatResource getById(Long id) {
        SleepStat sleepStat = sleepStatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find sleep stat with id " + id + "."));

        return SLEEP_STAT_MAPPER.toSleepStatResource(sleepStat);
    }

    @Override
    public List<QualityStatus> getAllQualityStatuses() {
        return List.of(QualityStatus.values());
    }

    @Override
    public List<UserSleepStatResource> getAllByUserId(Long userId) {
        List<SleepStat> sleepStats = sleepStatRepository.findAllByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find sleep stats for user with id " + userId + "."));

        return SLEEP_STAT_MAPPER.toUserSleepStatResources(sleepStats);
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

    private boolean isSleepStatUnchanged(SleepStatResource sleepStatResource, SleepStat sleepStat) {
        return sleepStatResource.getDuration().equals(sleepStat.getDuration())
                && sleepStatResource.getQualityStatus() == sleepStat.getQualityStatus()
                && sleepStatResource.getDate().equals(DateMapper.toString(sleepStat.getDate()));
    }

    @Override
    public SleepStatResource update(SleepStatResource sleepStatResource, Long id) {
        try {
            SleepStat sleepStat = sleepStatRepository.getReferenceById(id);

            if (isSleepStatUnchanged(sleepStatResource, sleepStat)) {
                throw new DuplicateEntityFieldException("Sleep stat with is unchanged.");
            }

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
