package pl.fitnote.body.measurements.bodyMasurement;

import pl.fitnote.commons.UserDetails;
import pl.fitnote.user.User;

import java.util.List;

public interface BodyMeasurementService {
    Long createBodyMeasurement(BodyMeasurementDto command, User requestingUser);

    void updateBodyMeasurement(Long bodyMeasurementId, BodyMeasurementDto command, UserDetails userDetails);

    List<BodyMeasurementProjection> getAllBodyMeasurements(UserDetails userDetails);

    <T> T getBodyMeasurement(Long bodyMeasurementId, UserDetails userDetails, Class<T> type);

    BodyMeasurementDto getUsersLatestBodyMeasurement(User requestingUser);

    void deleteBodyMeasurement(Long bodyMeasurementId, UserDetails userDetails);
}
