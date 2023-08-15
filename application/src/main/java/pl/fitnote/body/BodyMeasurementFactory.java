package pl.fitnote.body;

import org.springframework.stereotype.Service;

@Service
class BodyMeasurementFactory {


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
                .measurementDate(source.getMeasurementDate())
                .build();
    }
}
