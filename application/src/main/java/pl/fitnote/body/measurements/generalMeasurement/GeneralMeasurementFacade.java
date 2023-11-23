package pl.fitnote.body.measurements.generalMeasurement;


import pl.fitnote.commons.UserDetails;

import java.util.List;

public interface GeneralMeasurementFacade {
    Long createGeneralMeasurement(GeneralMeasurementDto command, UserDetails userDetails);

    void updateGeneralMeasurement(Long generalMeasurementId, GeneralMeasurementDto command, UserDetails userDetails);

    List<GeneralMeasurementProjection> getAllGeneralMeasurements(UserDetails userDetails);

    <T> T getGeneralMeasurement(Long generalMeasurementId, UserDetails userDetails, Class<T> type);

    GeneralMeasurementDto getUsersLatestGeneralMeasurement(UserDetails userDetails);

    void deleteGeneralMeasurement(Long generalMeasurementId, UserDetails userDetails);
}
