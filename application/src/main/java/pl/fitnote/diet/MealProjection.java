package pl.fitnote.diet;

import java.time.LocalDateTime;

public interface MealProjection {
    Long getId();
    String getName();
    Float getKilocalories();
    Float getProteins();
    Float getCarbohydrates();
    Float getFat();
    Float getSalt();
    LocalDateTime getMealDate();
}
