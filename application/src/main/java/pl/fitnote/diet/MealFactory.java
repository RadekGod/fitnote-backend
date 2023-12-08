package pl.fitnote.diet;

import org.springframework.stereotype.Service;
import pl.fitnote.user.User;

@Service
class MealFactory {
    Meal createMealFromDto(MealDto source, User user) {
        return Meal.builder()
                .name(source.getName())
                .kilocalories(source.getKilocalories())
                .proteins(source.getProteins())
                .carbohydrates(source.getCarbohydrates())
                .fat(source.getFat())
                .salt(source.getSalt())
                .mealDate(source.getMealDate())
                .user(user)
                .build();
    }

    Meal updateMealWithDto(Meal toUpdate, MealDto source) {
        return toUpdate.toBuilder()
                .name(source.getName())
                .kilocalories(source.getKilocalories())
                .proteins(source.getProteins())
                .carbohydrates(source.getCarbohydrates())
                .fat(source.getFat())
                .salt(source.getSalt())
                .mealDate(source.getMealDate())
                .build();
    }
}
