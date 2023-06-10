package org.elsys.health_tracker.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.elsys.health_tracker.controller.resources.UserWorkoutStatResource;
import org.elsys.health_tracker.controller.resources.WorkoutStatResource;
import org.elsys.health_tracker.entity.WorkoutStat;
import org.elsys.health_tracker.exception.DuplicateEntityFieldException;
import org.elsys.health_tracker.mapper.DateMapper;
import org.elsys.health_tracker.repository.WorkoutStatRepository;
import org.elsys.health_tracker.service.WorkoutStatService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public WorkoutStatResource getById(Long id) {
        WorkoutStat workoutStat = workoutStatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find workout stat with id: " + id + "."));

        return WORKOUT_STAT_MAPPER.toWorkoutStatResource(workoutStat);
    }

    @Override
    public List<UserWorkoutStatResource> getAllByUserId(Long userId) {
        List<WorkoutStat> workoutStats = workoutStatRepository.findAllByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find workout stats for user with id: " + userId + "."));

        return WORKOUT_STAT_MAPPER.toUserWorkoutStatResources(workoutStats);
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

    private boolean isWorkoutStatUnchanged(WorkoutStatResource workoutStatResource, WorkoutStat workoutStat) {
        return workoutStatResource.getDuration().equals(workoutStat.getDuration())
                && workoutStatResource.getBurnedCalories().equals(workoutStat.getBurnedCalories())
                && workoutStatResource.getWorkoutBio().equals(workoutStat.getWorkoutBio())
                && workoutStatResource.getDate().equals(DateMapper.toString(workoutStat.getDate()));
    }

    @Override
    public WorkoutStatResource update(WorkoutStatResource workoutStatResource, Long id) {
        try {
            WorkoutStat workoutStat = workoutStatRepository.getReferenceById(id);

            if (isWorkoutStatUnchanged(workoutStatResource, workoutStat)) {
                throw new DuplicateEntityFieldException("Workout stat is unchanged.");
            }

            workoutStat.setDuration(workoutStatResource.getDuration());
            workoutStat.setBurnedCalories(workoutStatResource.getBurnedCalories());
            workoutStat.setWorkoutBio(workoutStatResource.getWorkoutBio());
            workoutStat.setDate(DateMapper.toSQLDate(workoutStatResource.getDate()));

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
