package org.elsys.health_tracker.service.impl;

import lombok.RequiredArgsConstructor;
import org.elsys.health_tracker.controller.resources.WorkoutStatResource;
import org.elsys.health_tracker.entity.WorkoutStat;
import org.elsys.health_tracker.repository.WorkoutStatRepository;
import org.elsys.health_tracker.service.WorkoutStatService;
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
    public List<WorkoutStatResource> getAllByUserId(Long userId) {
        return WORKOUT_STAT_MAPPER.toWorkoutStatResources(workoutStatRepository.findAllByUserId(userId));
    }

    @Override
    public Optional<WorkoutStatResource> getById(Long id) {
        return workoutStatRepository.findById(id).map(WORKOUT_STAT_MAPPER::toWorkoutStatResource);
    }

    @Override
    public WorkoutStatResource create(WorkoutStatResource workoutStatResource) {
        WorkoutStat workoutStat = workoutStatRepository.save(WORKOUT_STAT_MAPPER.fromWorkoutStatResource(workoutStatResource));
        workoutStatResource.setId(workoutStat.getId());
        return workoutStatResource;
    }

    @Override
    public WorkoutStatResource update(WorkoutStatResource workoutStatResource, Long id) {
        WorkoutStat workoutStat = workoutStatRepository.getReferenceById(id);

        workoutStat.setDuration(workoutStatResource.getDuration());
        workoutStat.setBurnedCalories(workoutStatResource.getBurnedCalories());
        workoutStat.setWorkoutBio(workoutStatResource.getWorkoutBio());

        return WORKOUT_STAT_MAPPER.toWorkoutStatResource(workoutStatRepository.save(workoutStat));
    }

    @Override
    public void delete(Long id) {
        workoutStatRepository.deleteById(id);
    }
}
