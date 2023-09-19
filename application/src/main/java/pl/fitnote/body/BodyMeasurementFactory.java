package pl.fitnote.body;

import org.springframework.stereotype.Service;
import pl.fitnote.commons.MeasureUnitsCalculator;
import pl.fitnote.user.User;

@Service
class BodyMeasurementFactory {

    private final MeasureUnitsCalculator measureUnitsCalculator = new MeasureUnitsCalculator();

    BodyMeasurement createBodyMeasurementFromDto(BodyMeasurementDto source) {
        return BodyMeasurement.builder()
                .id(source.getId())
                .chest(source.getChest())
                .bicepsLeft(source.getBicepsLeft())
                .bicepsRight(source.getBicepsRight())
                .forearmLeft(source.getForearmLeft())
                .forearmRight(source.getForearmRight())
                .waist(source.getWaist())
                .hip(source.getHip())
                .thighLeft(source.getThighLeft())
                .thighRight(source.getThighRight())
                .calfLeft(source.getCalfLeft())
                .calfRight(source.getCalfRight())
                .lengthUnit(source.getLengthUnit())
                .measurementDate(source.getMeasurementDate())
                .build();
    }

    BodyMeasurement updateBodyMeasurementWithDto(BodyMeasurement toUpdate, BodyMeasurementDto source) {
        return toUpdate.toBuilder()
                .id(source.getId())
                .chest(source.getChest())
                .bicepsLeft(source.getBicepsLeft())
                .bicepsRight(source.getBicepsRight())
                .forearmLeft(source.getForearmLeft())
                .forearmRight(source.getForearmRight())
                .waist(source.getWaist())
                .hip(source.getHip())
                .thighLeft(source.getThighLeft())
                .thighRight(source.getThighRight())
                .calfLeft(source.getCalfLeft())
                .calfRight(source.getCalfRight())
                .lengthUnit(source.getLengthUnit())
                .measurementDate(source.getMeasurementDate())
                .build();
    }

    BodyMeasurementDto recalculateMeasurementValuesAndCreateDto(User user, BodyMeasurementProjection source) {
        return BodyMeasurementDto.builder()
                .id(source.getId())
                .chest(measureUnitsCalculator.recalculateLengthValue(user.getUserSettings().getLengthUnit(), source.getChest()))
                .bicepsLeft(measureUnitsCalculator.recalculateLengthValue(user.getUserSettings().getLengthUnit(), source.getBicepsLeft()))
                .bicepsRight(measureUnitsCalculator.recalculateLengthValue(user.getUserSettings().getLengthUnit(), source.getBicepsRight()))
                .forearmLeft(measureUnitsCalculator.recalculateLengthValue(user.getUserSettings().getLengthUnit(), source.getForearmLeft()))
                .forearmRight(measureUnitsCalculator.recalculateLengthValue(user.getUserSettings().getLengthUnit(), source.getForearmRight()))
                .waist(measureUnitsCalculator.recalculateLengthValue(user.getUserSettings().getLengthUnit(), source.getWaist()))
                .hip(measureUnitsCalculator.recalculateLengthValue(user.getUserSettings().getLengthUnit(), source.getHip()))
                .thighLeft(measureUnitsCalculator.recalculateLengthValue(user.getUserSettings().getLengthUnit(), source.getThighLeft()))
                .thighRight(measureUnitsCalculator.recalculateLengthValue(user.getUserSettings().getLengthUnit(), source.getThighRight()))
                .calfLeft(measureUnitsCalculator.recalculateLengthValue(user.getUserSettings().getLengthUnit(), source.getCalfLeft()))
                .calfRight(measureUnitsCalculator.recalculateLengthValue(user.getUserSettings().getLengthUnit(), source.getCalfRight()))
                .lengthUnit(user.getUserSettings().getLengthUnit())
                .measurementDate(source.getMeasurementDate())
                .build();
    }

    BodyMeasurementDto createDtoFromProjection(BodyMeasurementProjection source) {
        return BodyMeasurementDto.builder()
                .id(source.getId())
                .chest(source.getChest())
                .bicepsLeft(source.getBicepsLeft())
                .bicepsRight(source.getBicepsRight())
                .forearmLeft(source.getForearmLeft())
                .forearmRight(source.getForearmRight())
                .waist(source.getWaist())
                .hip(source.getHip())
                .thighLeft(source.getThighLeft())
                .thighRight(source.getThighRight())
                .calfLeft(source.getCalfLeft())
                .calfRight(source.getCalfRight())
                .lengthUnit(source.getLengthUnit())
                .measurementDate(source.getMeasurementDate())
                .build();
    }
}
