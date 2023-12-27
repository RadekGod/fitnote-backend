package pl.fitnote.diet;

import pl.fitnote.commons.UserDetails;

import java.util.List;

public interface MealFacade {
    Long createMeal(MealDto command, UserDetails userDetails);
    void updateMeal(Long mealId, MealDto command, UserDetails userDetails);
    List<MealProjection> getAllTodayMeals(UserDetails userDetails);
    <T> T getMeal(Long mealId, UserDetails userDetails, Class<T> type);
    void deleteMeal(Long mealId, UserDetails userDetails);
}
