package org.elsys.health_tracker.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.elsys.health_tracker.controller.resources.SleepStatResource;
import org.elsys.health_tracker.entity.SleepStat;
import org.elsys.health_tracker.repository.SleepStatRepository;
import org.elsys.health_tracker.service.SleepStatService;
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
    public List<SleepStatResource> getAllByUserId(Long userId) {
        return SLEEP_STAT_MAPPER.toSleepStatResources(sleepStatRepository.findAllByUserId(userId));
    }

    @Override
    public Optional<SleepStatResource> getById(Long id) {
        return sleepStatRepository.findById(id).map(SLEEP_STAT_MAPPER::toSleepStatResource);
    }

    @Override
    public SleepStatResource create(SleepStatResource sleepStatResource) {
        SleepStat sleepStat = sleepStatRepository.save(SLEEP_STAT_MAPPER.fromSleepStatResource(sleepStatResource));
        sleepStatResource.setId(sleepStat.getId());
        return sleepStatResource;
    }

    @Override
    public SleepStatResource update(SleepStatResource sleepStatResource, Long id) {
        SleepStat sleepStat = sleepStatRepository.getReferenceById(id);

        sleepStat.setDuration(sleepStatResource.getDuration());
        sleepStat.setQualityStatus(sleepStatResource.getQualityStatus());

        return SLEEP_STAT_MAPPER.toSleepStatResource(sleepStatRepository.save(sleepStat));
    }

    @Override
    public void delete(Long id) {
        sleepStatRepository.deleteById(id);
    }
}
