package pl.fitnote.body;

import java.time.LocalDateTime;

interface GeneralMeasurementProjection {
    Long getId();
    Float getWeight();
    Float getHeight();
    Float getBmi();
    Float getMuscleContent();
    Float getFatContent();
    LocalDateTime getMeasurementDate();
}
