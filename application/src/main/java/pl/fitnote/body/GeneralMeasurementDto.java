package pl.fitnote.body;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
class GeneralMeasurementDto {
    private Long id;
    private Float weight;
    private Float height;
//    private Float bmi;
    private Float muscleContent;
    private Float fatContent;
    private LocalDateTime measurementDate;
}
