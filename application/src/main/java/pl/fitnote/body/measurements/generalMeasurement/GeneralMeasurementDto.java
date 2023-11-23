package pl.fitnote.body.measurements.generalMeasurement;

import jakarta.validation.constraints.NotNull;
import lombok.*;
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
    @NotNull(message = "measurementDate is required")
    private LocalDateTime measurementDate;
}
