package pl.fitnote.diet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.fitnote.commons.UserDetails;
import pl.fitnote.user.User;
import pl.fitnote.user.UserFacade;

import java.util.List;

@Component
@RequiredArgsConstructor
class MealFacadeImpl implements MealFacade {

    private final UserFacade userFacade;
    private final MealService mealService;


    @Override
    public Long createMeal(MealDto command, UserDetails userDetails) {
        User requestingUser = userFacade.getUser(userDetails, User.class);
        return mealService.createMeal(command, requestingUser);
    }

    @Override
    public void updateMeal(Long mealId, MealDto command, UserDetails userDetails) {
        Meal mealToUpdate = getMeal(mealId, userDetails, Meal.class);
        mealService.updateMeal(mealToUpdate, command);
    }

    @Override
    public List<MealProjection> getAllTodayMeals(UserDetails userDetails) {
        return mealService.getAllTodayMeals(userDetails);
    }

    @Override
    public <T> T getMeal(Long mealId, UserDetails userDetails, Class<T> type) {
        return mealService.getMeal(mealId, userDetails, type);
    }

    @Override
    public void deleteMeal(Long mealId, UserDetails userDetails) {
        mealService.deleteMeal(mealId, userDetails);
    }
}
