package pl.fitnote.body;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fitnote.commons.UserDetails;
import pl.fitnote.user.User;
import pl.fitnote.user.UserFacade;

import java.util.List;

@Service
@RequiredArgsConstructor
class BodyFacadeImpl implements BodyFacade {

    private final UserFacade userFacade;
    private final GeneralMeasurementFactory generalMeasurementFactory;
    private final BodyMeasurementFactory bodyMeasurementFactory;
    private final BodyMeasurementQueryRepository bodyMeasurementQueryRepository;
    private final BodyMeasurementPersistRepository bodyMeasurementPersistRepository;
    private final GeneralMeasurementQueryRepository generalMeasurementQueryRepository;
    private final GeneralMeasurementPersistRepository generalMeasurementPersistRepository;

    @Override
    @Transactional
    public Long createBodyMeasurement(final BodyMeasurementDto command, final UserDetails userDetails) {
        User requestingUser = userFacade.getUser(userDetails, User.class);
        BodyMeasurement bodyMeasurementToSave = bodyMeasurementFactory.createBodyMeasurementFromDto(command);
        bodyMeasurementToSave.setUser(requestingUser);
        return bodyMeasurementPersistRepository.save(bodyMeasurementToSave).getId();
    }

    @Override
    @Transactional
    public void updateBodyMeasurement(final Long bodyMeasurementId, final BodyMeasurementDto command, final UserDetails userDetails) {
        BodyMeasurement bodyMeasurementToUpdate = bodyMeasurementQueryRepository.findBodyMeasurementByGivenIdAndKeycloakId(bodyMeasurementId, userDetails.getKeycloakId(), BodyMeasurement.class)
                .orElseThrow(EntityNotFoundException::new);

        bodyMeasurementPersistRepository.save(bodyMeasurementFactory.updateBodyMeasurementWithDto(bodyMeasurementToUpdate, command));
    }

    @Override
    public <T> T getBodyMeasurement(final Long bodyMeasurementId, final UserDetails userDetails, final Class<T> type) {
        return bodyMeasurementQueryRepository.findBodyMeasurementByGivenIdAndKeycloakId(bodyMeasurementId, userDetails.getKeycloakId(), type)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<BodyMeasurementProjection> getAllBodyMeasurements(final UserDetails userDetails) {
        return bodyMeasurementQueryRepository.findAllBodyMeasurementsByGivenKeycloakId(userDetails.getKeycloakId());
    }

    @Override
    @Transactional
    public void deleteBodyMeasurement(final Long bodyMeasurementId, final UserDetails userDetails) {
        BodyMeasurement bodyMeasurementToUpdate = bodyMeasurementQueryRepository.findBodyMeasurementByGivenIdAndKeycloakId(bodyMeasurementId, userDetails.getKeycloakId(), BodyMeasurement.class)
                .orElseThrow(EntityNotFoundException::new);
        bodyMeasurementPersistRepository.delete(bodyMeasurementToUpdate);
    }


    @Override
    @Transactional
    public Long createGeneralMeasurement(final GeneralMeasurementDto command, final UserDetails userDetails) {
        User requestingUser = userFacade.getUser(userDetails, User.class);
        GeneralMeasurement generalMeasurementToSave = generalMeasurementFactory.createGeneralMeasurementFromDto(command);
        generalMeasurementToSave.setUser(requestingUser);
        return generalMeasurementPersistRepository.save(generalMeasurementToSave).getId();
    }

    @Override
    @Transactional
    public void updateGeneralMeasurement(final Long generalMeasurementId, final GeneralMeasurementDto command, final UserDetails userDetails) {
        GeneralMeasurement generalMeasurementToUpdate = generalMeasurementQueryRepository.findGeneralMeasurementByGivenIdAndKeycloakId(generalMeasurementId, userDetails.getKeycloakId(), GeneralMeasurement.class)
                .orElseThrow(EntityNotFoundException::new);

        generalMeasurementPersistRepository.save(generalMeasurementFactory.updateGeneralMeasurementWithDto(generalMeasurementToUpdate, command));
    }

    @Override
    public <T> T getGeneralMeasurement(final Long generalMeasurementId, final UserDetails userDetails, final Class<T> type) {
        return generalMeasurementQueryRepository.findGeneralMeasurementByGivenIdAndKeycloakId(generalMeasurementId, userDetails.getKeycloakId(), type)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<GeneralMeasurementProjection> getAllGeneralMeasurements(final UserDetails userDetails) {
        return generalMeasurementQueryRepository.findAllGeneralMeasurementsByGivenKeycloakId(userDetails.getKeycloakId());
    }

    @Override
    @Transactional
    public void deleteGeneralMeasurement(final Long generalMeasurementId, final UserDetails userDetails) {
        GeneralMeasurement generalMeasurement = generalMeasurementQueryRepository.findGeneralMeasurementByGivenIdAndKeycloakId(generalMeasurementId, userDetails.getKeycloakId(), GeneralMeasurement.class)
                .orElseThrow(EntityNotFoundException::new);
        generalMeasurementPersistRepository.delete(generalMeasurement);
    }
}
