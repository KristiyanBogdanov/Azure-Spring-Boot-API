package org.elsys.health_tracker.service.impl;

import lombok.RequiredArgsConstructor;
import org.elsys.health_tracker.controller.resources.MealStatResource;
import org.elsys.health_tracker.entity.MealStat;
import org.elsys.health_tracker.repository.MealStatRepository;
import org.elsys.health_tracker.service.MealService;
import org.elsys.health_tracker.service.MealStatService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.elsys.health_tracker.mapper.MealStatMapper.MEAL_STAT_MAPPER;

@Service
@RequiredArgsConstructor
public class MealStatServiceImpl implements MealStatService {
    private final MealStatRepository mealStatRepository;
    private final MealService mealService;

    @Override
    public List<MealStatResource> getAll() {
        return MEAL_STAT_MAPPER.toMealStatResources(mealStatRepository.findAll());
    }

    @Override
    public List<MealStatResource> getAllByUserId(Long userId) {
        return MEAL_STAT_MAPPER.toMealStatResources(mealStatRepository.findAllByUserId(userId));
    }

    @Override
    public Optional<MealStatResource> getById(Long id) {
        return mealStatRepository.findById(id).map(MEAL_STAT_MAPPER::toMealStatResource);
    }

    @Override
    public MealStatResource create(MealStatResource mealStatResource) {
        MealStat mealStat = mealStatRepository.save(MEAL_STAT_MAPPER.fromMealStatResource(mealStatResource));
        mealStatResource.setId(mealStat.getId());
        return mealStatResource;
    }

    @Override
    public MealStatResource update(MealStatResource mealStatResource, Long id) {
        MealStat mealStat = mealStatRepository.getReferenceById(id);

        // future consideration needed
        mealService.update(mealStatResource.getMeal(), mealStat.getMeal().getId());

        return MEAL_STAT_MAPPER.toMealStatResource(mealStatRepository.save(mealStat));
    }

    @Override
    public void delete(Long id) {
        mealStatRepository.deleteById(id);
    }
}
