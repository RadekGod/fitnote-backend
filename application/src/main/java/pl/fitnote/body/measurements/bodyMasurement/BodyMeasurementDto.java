package pl.fitnote.body.measurements.bodyMasurement;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import pl.fitnote.user.LengthUnit;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
class BodyMeasurementDto {
    private Long id;
    private Float chest;
    private Float bicepsLeft;
    private Float bicepsRight;
    private Float forearmLeft;
    private Float forearmRight;
    private Float waist;
    private Float hip;
    private Float thighLeft;
    private Float thighRight;
    private Float calfLeft;
    private Float calfRight;
    private LengthUnit lengthUnit;
    @NotNull(message = "measurementDate is required")
    private LocalDateTime measurementDate;
}
