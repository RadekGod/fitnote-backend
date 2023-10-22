package pl.fitnote.body.measurements;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.fitnote.user.LengthUnit;
import pl.fitnote.user.WeightUnit;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
class GeneralMeasurementDto {
    private Long id;
    private Float weight;
    private Float height;
    private Float bmi;
    private Float muscleContent;
    private Float bodyFat;
    private WeightUnit weightUnit;
    private LengthUnit lengthUnit;
    private LocalDateTime measurementDate;
}
