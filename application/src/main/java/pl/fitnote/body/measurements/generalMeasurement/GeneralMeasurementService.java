package pl.fitnote.body.measurements.generalMeasurement;


import pl.fitnote.commons.UserDetails;
import pl.fitnote.user.User;

import java.util.List;

public interface GeneralMeasurementService {
    Long createGeneralMeasurement(GeneralMeasurementDto command, User requestingUser);

    void updateGeneralMeasurement(Long generalMeasurementId, GeneralMeasurementDto command, UserDetails userDetails);

    List<GeneralMeasurementProjection> getAllGeneralMeasurements(UserDetails userDetails);

    <T> T getGeneralMeasurement(Long generalMeasurementId, UserDetails userDetails, Class<T> type);

    GeneralMeasurementDto getUsersLatestGeneralMeasurement(User requestingUser);

    void deleteGeneralMeasurement(Long generalMeasurementId, UserDetails userDetails);
}
