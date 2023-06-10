package org.elsys.health_tracker.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.elsys.health_tracker.controller.resources.MealStatResource;
import org.elsys.health_tracker.controller.resources.UserMealStatResource;
import org.elsys.health_tracker.entity.MealStat;
import org.elsys.health_tracker.exception.DuplicateEntityFieldException;
import org.elsys.health_tracker.mapper.DateMapper;
import org.elsys.health_tracker.repository.MealStatRepository;
import org.elsys.health_tracker.service.MealStatService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.elsys.health_tracker.mapper.MealStatMapper.MEAL_STAT_MAPPER;

@Service
@RequiredArgsConstructor
public class MealStatServiceImpl implements MealStatService {
    private final MealStatRepository mealStatRepository;

    @Override
    public List<MealStatResource> getAll() {
        return MEAL_STAT_MAPPER.toMealStatResources(mealStatRepository.findAll());
    }

    @Override
    public MealStatResource getById(Long id) {
        MealStat mealStat = mealStatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find meal stat with id " + id + "."));

        return MEAL_STAT_MAPPER.toMealStatResource(mealStat);
    }

    @Override
    public List<UserMealStatResource> getAllByUserId(Long userId) {
        List<MealStat> mealStats = mealStatRepository.findAllByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find meal stats for user with id " + userId + "."));

        return MEAL_STAT_MAPPER.toUserMealStatResources(mealStats);
    }

    @Override
    public MealStatResource create(MealStatResource mealStatResource) {
        try {
            MealStat mealStat = mealStatRepository.save(MEAL_STAT_MAPPER.fromMealStatResource(mealStatResource));
            mealStatResource.setId(mealStat.getId());
            return mealStatResource;
        } catch (DataIntegrityViolationException e) {
            throw new EntityNotFoundException("Foreign key constraint violation.");
        }
    }

    private boolean isMealStatUnchanged(MealStatResource mealStatResource, MealStat mealStat) {
        return mealStatResource.getDate().equals(DateMapper.toString(mealStat.getDate()));
    }

    @Override
    public MealStatResource update(MealStatResource mealStatResource, Long id) {
        try {
            MealStat mealStat = mealStatRepository.getReferenceById(id);

            if (isMealStatUnchanged(mealStatResource, mealStat)) {
                throw new DuplicateEntityFieldException("Meal stat is unchanged.");
            }

            // TODO: add update logic for meal?
            mealStat.setDate(DateMapper.toSQLDate(mealStatResource.getDate()));

            return MEAL_STAT_MAPPER.toMealStatResource(mealStatRepository.save(mealStat));
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Unable to find meal stat with id " + id + ".");
        }
    }

    @Override
    public void delete(Long id) {
        if (mealStatRepository.existsById(id)) {
            mealStatRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Unable to find meal stat with id " + id + ".");
        }
    }
}
