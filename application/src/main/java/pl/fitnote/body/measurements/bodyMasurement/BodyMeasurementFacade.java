package pl.fitnote.body.measurements.bodyMasurement;

import pl.fitnote.commons.UserDetails;

import java.util.List;

public interface BodyMeasurementFacade {
    Long createBodyMeasurement(BodyMeasurementDto command, UserDetails userDetails);

    void updateBodyMeasurement(Long bodyMeasurementId, BodyMeasurementDto command, UserDetails userDetails);

    List<BodyMeasurementProjection> getAllBodyMeasurements(UserDetails userDetails);

    <T> T getBodyMeasurement(Long bodyMeasurementId, UserDetails userDetails, Class<T> type);

    BodyMeasurementDto getUsersLatestBodyMeasurement(UserDetails userDetails);

    void deleteBodyMeasurement(Long bodyMeasurementId, UserDetails userDetails);
}
