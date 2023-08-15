package pl.fitnote.body;

import java.time.LocalDateTime;

interface BodyMeasurementProjection {
    Long getId();
    Float getChest();
    Float getBicepsLeft();
    Float getBicepsRight();
    Float getForearmLeft();
    Float getForearmRight();
    Float getWaist();
    Float getHip();
    Float getThighLeft();
    Float getThighRight();
    Float getCalfLeft();
    Float getCalfRight();
    LocalDateTime getMeasurementDate();
}
