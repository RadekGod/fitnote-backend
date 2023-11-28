package pl.fitnote.activity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityDto {
    private Long id;
    private Float activityDurationInMinutes;
    private String trainingPlanName;
    private Integer burntCalories;
    private Float distanceTraveled;
    private LocalDateTime activityDate;
    private ActivityTypeDto activityType;
}
