package pl.fitnote.diet;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MealDto {
    private Long id;
    private String name;
    private Float kilocalories;
    private Float proteins;
    private Float carbohydrates;
    private Float fat;
    private Float salt;
    private LocalDateTime mealDate;
}
