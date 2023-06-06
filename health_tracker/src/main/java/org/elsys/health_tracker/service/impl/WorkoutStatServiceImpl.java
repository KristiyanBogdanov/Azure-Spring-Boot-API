package org.elsys.health_tracker.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.elsys.health_tracker.controller.resources.UserWorkoutStatResource;
import org.elsys.health_tracker.controller.resources.WorkoutStatResource;
import org.elsys.health_tracker.entity.WorkoutStat;
import org.elsys.health_tracker.repository.WorkoutStatRepository;
import org.elsys.health_tracker.service.WorkoutStatService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.elsys.health_tracker.mapper.WorkoutStatMapper.WORKOUT_STAT_MAPPER;

@Service
@RequiredArgsConstructor
public class WorkoutStatServiceImpl implements WorkoutStatService {
    private final WorkoutStatRepository workoutStatRepository;

    @Override
    public List<WorkoutStatResource> getAll() {
        return WORKOUT_STAT_MAPPER.toWorkoutStatResources(workoutStatRepository.findAll());
    }

    @Override
    public Optional<WorkoutStatResource> getById(Long id) {
        return workoutStatRepository.findById(id).map(WORKOUT_STAT_MAPPER::toWorkoutStatResource);
    }

    @Override
    public Optional<List<UserWorkoutStatResource>> getAllByUserId(Long userId) {
        return workoutStatRepository.findAllByUserId(userId).map(WORKOUT_STAT_MAPPER::toUserWorkoutStatResources);
    }

    @Override
    public WorkoutStatResource create(WorkoutStatResource workoutStatResource) {
        try {
            WorkoutStat workoutStat = workoutStatRepository.save(WORKOUT_STAT_MAPPER.fromWorkoutStatResource(workoutStatResource));
            workoutStatResource.setId(workoutStat.getId());
            return workoutStatResource;
        } catch (DataIntegrityViolationException e) {
            throw new EntityNotFoundException("Foreign key constraint violation.");
        }
    }

    @Override
    public WorkoutStatResource update(WorkoutStatResource workoutStatResource, Long id) {
        try {
            WorkoutStat workoutStat = workoutStatRepository.getReferenceById(id);

            workoutStat.setDuration(workoutStatResource.getDuration());
            workoutStat.setBurnedCalories(workoutStatResource.getBurnedCalories());
            workoutStat.setWorkoutBio(workoutStatResource.getWorkoutBio());

            return WORKOUT_STAT_MAPPER.toWorkoutStatResource(workoutStatRepository.save(workoutStat));
        } catch (DataIntegrityViolationException e) {
            throw new EntityNotFoundException("Unable to find workout stat with id: " + id + ".");
        }
    }

    @Override
    public void delete(Long id) {
        if (workoutStatRepository.existsById(id)) {
            workoutStatRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Unable to find workout stat with id: " + id + ".");
        }
    }
}
