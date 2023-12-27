package pl.fitnote.body.measurements.generalMeasurement;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fitnote.commons.UserDetails;
import pl.fitnote.user.LengthUnit;
import pl.fitnote.user.User;
import pl.fitnote.user.WeightUnit;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneralMeasurementServiceImpl implements GeneralMeasurementService {

    private final GeneralMeasurementFactory generalMeasurementFactory;
    private final GeneralMeasurementQueryRepository generalMeasurementQueryRepository;
    private final GeneralMeasurementPersistRepository generalMeasurementPersistRepository;

    @Override
    @Transactional
    public Long createGeneralMeasurement(final GeneralMeasurementDto command, final User requestingUser) {
        GeneralMeasurement generalMeasurementToSave = generalMeasurementFactory.createGeneralMeasurementFromDto(command);
        generalMeasurementToSave.setUser(requestingUser);
        return generalMeasurementPersistRepository.save(generalMeasurementToSave).getId();
    }

    @Override
    @Transactional
    public void updateGeneralMeasurement(final Long generalMeasurementId, final GeneralMeasurementDto command, final UserDetails userDetails) {
        GeneralMeasurement generalMeasurementToUpdate = generalMeasurementQueryRepository.findByIdAndUserEmail(generalMeasurementId, userDetails.getEmail(), GeneralMeasurement.class)
                .orElseThrow(EntityNotFoundException::new);
        generalMeasurementPersistRepository.save(generalMeasurementFactory.updateGeneralMeasurementWithDto(generalMeasurementToUpdate, command));
    }

    @Override
    public <T> T getGeneralMeasurement(final Long generalMeasurementId, final UserDetails userDetails, final Class<T> type) {
        return generalMeasurementQueryRepository.findByIdAndUserEmail(generalMeasurementId, userDetails.getEmail(), type)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public GeneralMeasurementDto getUsersLatestGeneralMeasurement(final User requestingUser) {
        GeneralMeasurementProjection generalMeasurementProjection = generalMeasurementQueryRepository.findLatestGeneralMeasurementByGivenEmail(requestingUser.getEmail(), GeneralMeasurementProjection.class)
                .orElseThrow(EntityNotFoundException::new);
        if (!measurementLengthMatchWithUsersLength(generalMeasurementProjection.getLengthUnit(), requestingUser.getUserSettings().getLengthUnit()) ||
                !measurementWeightMatchWithUsersWeight(generalMeasurementProjection.getWeightUnit(), requestingUser.getUserSettings().getWeightUnit())) {
            return generalMeasurementFactory.recalculateMeasurementValuesAndCreateDto(requestingUser, generalMeasurementProjection);
        }
        return generalMeasurementFactory.createDtoFromProjection(generalMeasurementProjection);
    }

    @Override
    public List<GeneralMeasurementProjection> getAllGeneralMeasurements(final UserDetails userDetails) {
        return generalMeasurementQueryRepository.findAllByUserEmailOrderByMeasurementDateDesc(userDetails.getEmail());
    }

    @Override
    @Transactional
    public void deleteGeneralMeasurement(final Long generalMeasurementId, final UserDetails userDetails) {
        GeneralMeasurement generalMeasurement = generalMeasurementQueryRepository.findByIdAndUserEmail(generalMeasurementId, userDetails.getEmail(), GeneralMeasurement.class)
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
