package pl.fitnote.body.measurements.generalMeasurement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.fitnote.commons.UserDetails;
import pl.fitnote.user.User;
import pl.fitnote.user.UserFacade;

import java.util.List;

@Component
@RequiredArgsConstructor
class GeneralMeasurementFacadeImpl implements GeneralMeasurementFacade {

    private final UserFacade userFacade;
    private final GeneralMeasurementService generalMeasurementService;

    @Override
    public Long createGeneralMeasurement(final GeneralMeasurementDto command, final UserDetails userDetails) {
        User requestingUser = userFacade.getUser(userDetails, User.class);
        return generalMeasurementService.createGeneralMeasurement(command, requestingUser);
    }

    @Override
    public void updateGeneralMeasurement(final Long generalMeasurementId, final GeneralMeasurementDto command, final UserDetails userDetails) {
        generalMeasurementService.updateGeneralMeasurement(generalMeasurementId, command, userDetails);
    }

    @Override
    public <T> T getGeneralMeasurement(final Long generalMeasurementId, final UserDetails userDetails, final Class<T> type) {
        return generalMeasurementService.getGeneralMeasurement(generalMeasurementId, userDetails, type);
    }

    @Override
    public GeneralMeasurementDto getUsersLatestGeneralMeasurement(final UserDetails userDetails) {
        User requestingUser = userFacade.getUser(userDetails, User.class);
        return generalMeasurementService.getUsersLatestGeneralMeasurement(requestingUser);
    }

    @Override
    public List<GeneralMeasurementProjection> getAllGeneralMeasurements(final UserDetails userDetails) {
        return generalMeasurementService.getAllGeneralMeasurements(userDetails);
    }

    @Override
    public void deleteGeneralMeasurement(final Long generalMeasurementId, final UserDetails userDetails) {
        generalMeasurementService.deleteGeneralMeasurement(generalMeasurementId, userDetails);
    }
}
