package pl.fitnote.body.measurements;

import pl.fitnote.user.LengthUnit;
import pl.fitnote.user.WeightUnit;

import java.time.LocalDateTime;

interface GeneralMeasurementProjection {
    Long getId();

    Float getWeight();

    Float getHeight();

    Float getBmi();

    Float getMuscleContent();

    Float getBodyFat();

    WeightUnit getWeightUnit();

    LengthUnit getLengthUnit();

    LocalDateTime getMeasurementDate();
}
