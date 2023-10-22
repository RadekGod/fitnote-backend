package pl.fitnote.body.measurements;

import pl.fitnote.commons.UserDetails;

import java.util.List;

public interface BodyFacade {
    Long createBodyMeasurement(BodyMeasurementDto command, UserDetails userDetails);

    void updateBodyMeasurement(Long bodyMeasurementId, BodyMeasurementDto command, UserDetails userDetails);

    List<BodyMeasurementProjection> getAllBodyMeasurements(UserDetails userDetails);

    <T> T getBodyMeasurement(Long bodyMeasurementId, UserDetails userDetails, Class<T> type);

    BodyMeasurementDto getUsersLatestBodyMeasurement(UserDetails userDetails);

    void deleteBodyMeasurement(Long bodyMeasurementId, UserDetails userDetails);

    Long createGeneralMeasurement(GeneralMeasurementDto command, UserDetails userDetails);

    void updateGeneralMeasurement(Long generalMeasurementId, GeneralMeasurementDto command, UserDetails userDetails);

    List<GeneralMeasurementProjection> getAllGeneralMeasurements(UserDetails userDetails);

    <T> T getGeneralMeasurement(Long generalMeasurementId, UserDetails userDetails, Class<T> type);

    GeneralMeasurementDto getUsersLatestGeneralMeasurement(UserDetails userDetails);

    void deleteGeneralMeasurement(Long generalMeasurementId, UserDetails userDetails);
}
