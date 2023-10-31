package pl.fitnote.body.measurements;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fitnote.commons.UserDetails;
import pl.fitnote.user.LengthUnit;
import pl.fitnote.user.User;
import pl.fitnote.user.UserFacade;
import pl.fitnote.user.WeightUnit;

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
        User requestingUser = userFacade.getUser(userDetails.getEmail(), User.class);
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
    public BodyMeasurementDto getUsersLatestBodyMeasurement(final UserDetails userDetails) {
        User requestingUser = userFacade.getUser(userDetails, User.class);
        BodyMeasurementProjection bodyMeasurementProjection = bodyMeasurementQueryRepository.findTop1ByUserEmailOrderByMeasurementDateDescIdDesc(userDetails.getEmail(), BodyMeasurementProjection.class)
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
        GeneralMeasurement generalMeasurementToUpdate = generalMeasurementQueryRepository.findGeneralMeasurementByGivenIdAndEmail(generalMeasurementId, userDetails.getEmail(), GeneralMeasurement.class)
                .orElseThrow(EntityNotFoundException::new);
        generalMeasurementPersistRepository.save(generalMeasurementFactory.updateGeneralMeasurementWithDto(generalMeasurementToUpdate, command));
    }

    @Override
    public <T> T getGeneralMeasurement(final Long generalMeasurementId, final UserDetails userDetails, final Class<T> type) {
        return generalMeasurementQueryRepository.findGeneralMeasurementByGivenIdAndEmail(generalMeasurementId, userDetails.getEmail(), type)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public GeneralMeasurementDto getUsersLatestGeneralMeasurement(final UserDetails userDetails) {
        User requestingUser = userFacade.getUser(userDetails, User.class);
        GeneralMeasurementProjection generalMeasurementProjection = generalMeasurementQueryRepository.findLatestGeneralMeasurementByGivenEmail(userDetails.getEmail(), GeneralMeasurementProjection.class)
                .orElseThrow(EntityNotFoundException::new);
        if (!measurementLengthMatchWithUsersLength(generalMeasurementProjection.getLengthUnit(), requestingUser.getUserSettings().getLengthUnit()) ||
                !measurementWeightMatchWithUsersWeight(generalMeasurementProjection.getWeightUnit(), requestingUser.getUserSettings().getWeightUnit())) {
            return generalMeasurementFactory.recalculateMeasurementValuesAndCreateDto(requestingUser, generalMeasurementProjection);
        }
        return generalMeasurementFactory.createDtoFromProjection(generalMeasurementProjection);
    }

    @Override
    public List<GeneralMeasurementProjection> getAllGeneralMeasurements(final UserDetails userDetails) {
        return generalMeasurementQueryRepository.findAllGeneralMeasurementsByGivenEmail(userDetails.getEmail());
    }

    @Override
    @Transactional
    public void deleteGeneralMeasurement(final Long generalMeasurementId, final UserDetails userDetails) {
        GeneralMeasurement generalMeasurement = generalMeasurementQueryRepository.findGeneralMeasurementByGivenIdAndEmail(generalMeasurementId, userDetails.getEmail(), GeneralMeasurement.class)
                .orElseThrow(EntityNotFoundException::new);
        generalMeasurementPersistRepository.delete(generalMeasurement);
    }

    private Boolean measurementLengthMatchWithUsersLength(LengthUnit measurementLength, LengthUnit userLength) {
        return measurementLength.equals(userLength);
    }

    private Boolean measurementWeightMatchWithUsersWeight(WeightUnit measurementWeight, WeightUnit userWeight) {
        return measurementWeight.equals(userWeight);
    }
}
