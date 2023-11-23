package pl.fitnote.body.measurements.bodyMasurement;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fitnote.commons.UserDetails;
import pl.fitnote.user.LengthUnit;
import pl.fitnote.user.User;

import java.util.List;

@Service
@RequiredArgsConstructor
class BodyMeasurementServiceImpl implements BodyMeasurementService {

    private final BodyMeasurementFactory bodyMeasurementFactory;
    private final BodyMeasurementQueryRepository bodyMeasurementQueryRepository;
    private final BodyMeasurementPersistRepository bodyMeasurementPersistRepository;

    @Override
    @Transactional
    public Long createBodyMeasurement(final BodyMeasurementDto command, final User requestingUser) {
        BodyMeasurement bodyMeasurementToSave = bodyMeasurementFactory.createBodyMeasurementFromDto(command);
        bodyMeasurementToSave.setUser(requestingUser);
        return bodyMeasurementPersistRepository.save(bodyMeasurementToSave).getId();
    }

    @Override
    @Transactional
    public void updateBodyMeasurement(final Long bodyMeasurementId, final BodyMeasurementDto command, final UserDetails userDetails) {
        BodyMeasurement bodyMeasurementToUpdate = bodyMeasurementQueryRepository.findByIdAndUserEmail(bodyMeasurementId, userDetails.getEmail(), BodyMeasurement.class)
                .orElseThrow(EntityNotFoundException::new);

        bodyMeasurementPersistRepository.save(bodyMeasurementFactory.updateBodyMeasurementWithDto(bodyMeasurementToUpdate, command));
    }

    @Override
    public <T> T getBodyMeasurement(final Long bodyMeasurementId, final UserDetails userDetails, final Class<T> type) {
        return bodyMeasurementQueryRepository.findByIdAndUserEmail(bodyMeasurementId, userDetails.getEmail(), type)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public BodyMeasurementDto getUsersLatestBodyMeasurement(final User requestingUser) {
        BodyMeasurementProjection bodyMeasurementProjection = bodyMeasurementQueryRepository.findTop1ByUserEmailOrderByMeasurementDateDescIdDesc(requestingUser.getEmail(), BodyMeasurementProjection.class)
                .orElseThrow(EntityNotFoundException::new);
        if (!measurementLengthMatchWithUsersLength(bodyMeasurementProjection.getLengthUnit(), requestingUser.getUserSettings().getLengthUnit())) {
            return bodyMeasurementFactory.recalculateMeasurementValuesAndCreateDto(requestingUser, bodyMeasurementProjection);
        }
        return bodyMeasurementFactory.createDtoFromProjection(bodyMeasurementProjection);
    }

    @Override
    public List<BodyMeasurementProjection> getAllBodyMeasurements(final UserDetails userDetails) {
        return bodyMeasurementQueryRepository.findAllByUserEmail(userDetails.getEmail());
    }

    @Override
    @Transactional
    public void deleteBodyMeasurement(final Long bodyMeasurementId, final UserDetails userDetails) {
        BodyMeasurement bodyMeasurementToUpdate = bodyMeasurementQueryRepository.findByIdAndUserEmail(bodyMeasurementId, userDetails.getEmail(), BodyMeasurement.class)
                .orElseThrow(EntityNotFoundException::new);
        bodyMeasurementPersistRepository.delete(bodyMeasurementToUpdate);
    }

    private Boolean measurementLengthMatchWithUsersLength(LengthUnit measurementLength, LengthUnit userLength) {
        return measurementLength.equals(userLength);
    }
}
