package pl.fitnote.diet;

import pl.fitnote.commons.UserDetails;
import pl.fitnote.user.User;

import java.util.List;

public interface MealService {
    Long createMeal(MealDto command, User requestingUser);
    void updateMeal(Meal mealToUpdate, MealDto command);
    List<MealProjection> getAllTodayMeals(UserDetails userDetails);
    <T> T getMeal(Long mealId, UserDetails userDetails, Class<T> type);
    void deleteMeal(Long mealId, UserDetails userDetails);
}
