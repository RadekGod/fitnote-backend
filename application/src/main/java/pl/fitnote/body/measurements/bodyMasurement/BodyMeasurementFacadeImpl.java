package pl.fitnote.body.measurements.bodyMasurement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fitnote.commons.UserDetails;
import pl.fitnote.user.User;
import pl.fitnote.user.UserFacade;

import java.util.List;

@Service
@RequiredArgsConstructor
class BodyMeasurementFacadeImpl implements BodyMeasurementFacade {

    private final UserFacade userFacade;
    private final BodyMeasurementService bodyMeasurementService;

    @Override
    @Transactional
    public Long createBodyMeasurement(final BodyMeasurementDto command, final UserDetails userDetails) {
        User requestingUser = userFacade.getUser(userDetails.getEmail(), User.class);
        return bodyMeasurementService.createBodyMeasurement(command, requestingUser);
    }

    @Override
    @Transactional
    public void updateBodyMeasurement(final Long bodyMeasurementId, final BodyMeasurementDto command, final UserDetails userDetails) {
        bodyMeasurementService.updateBodyMeasurement(bodyMeasurementId, command, userDetails);
    }

    @Override
    public <T> T getBodyMeasurement(final Long bodyMeasurementId, final UserDetails userDetails, final Class<T> type) {
        return bodyMeasurementService.getBodyMeasurement(bodyMeasurementId, userDetails, type);
    }

    @Override
    public BodyMeasurementDto getUsersLatestBodyMeasurement(final UserDetails userDetails) {
        User requestingUser = userFacade.getUser(userDetails, User.class);
        return bodyMeasurementService.getUsersLatestBodyMeasurement(requestingUser);
    }

    @Override
    public List<BodyMeasurementProjection> getAllBodyMeasurements(final UserDetails userDetails) {
        return bodyMeasurementService.getAllBodyMeasurements(userDetails);
    }

    @Override
    @Transactional
    public void deleteBodyMeasurement(final Long bodyMeasurementId, final UserDetails userDetails) {
        bodyMeasurementService.deleteBodyMeasurement(bodyMeasurementId, userDetails);
    }
}
