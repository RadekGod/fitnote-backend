package pl.fitnote.diet;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.fitnote.commons.UserDetails;
import pl.fitnote.user.User;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
class MealServiceImpl implements MealService {

    private final MealFactory mealFactory;
    private final MealPersistRepository mealPersistRepository;
    private final MealQueryRepository mealQueryRepository;

    @Override
    @Transactional
    public Long createMeal(MealDto command, User requestingUser) {
        Meal mealToSave = mealFactory.createMealFromDto(command, requestingUser);
        return mealPersistRepository.save(mealToSave).getId();
    }

    @Override
    @Transactional
    public void updateMeal(Meal mealToUpdate, MealDto command) {
        mealPersistRepository.save(mealFactory.updateMealWithDto(mealToUpdate, command));
    }

    @Override
    public List<MealProjection> getAllTodayMeals(UserDetails userDetails) {
        LocalDateTime timeFrom = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime timeTo = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
        return mealQueryRepository.findAllTodayMeals(userDetails.getEmail(), timeFrom, timeTo);
    }

    @Override
    public <T> T getMeal(Long mealId, UserDetails userDetails, Class<T> type) {
        return mealQueryRepository.findByIdAndUserEmail(mealId, userDetails.getEmail(), type)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public void deleteMeal(Long mealId, UserDetails userDetails) {
        Meal mealToDelete = mealQueryRepository.findByIdAndUserEmail(mealId, userDetails.getEmail(), Meal.class)
                .orElseThrow(EntityNotFoundException::new);
        mealPersistRepository.delete(mealToDelete);
    }
}
