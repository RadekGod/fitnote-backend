package pl.fitnote.activity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityTypeDto {
    private Long id;
    private String name;
    private Float averageCaloriesBurntPerHour;
    private Boolean distanceActivity;
}
