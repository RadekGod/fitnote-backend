package pl.fitnote.body;

import org.springframework.stereotype.Service;

@Service
class GeneralMeasurementFactory {
    GeneralMeasurement createGeneralMeasurementFromDto(GeneralMeasurementDto source) {
        return GeneralMeasurement.builder()
                .id(source.getId())
                .weight(source.getWeight())
                .height(source.getHeight())
                .bmi(calculateBmi(source.getWeight(), source.getHeight()))
                .muscleContent(source.getMuscleContent())
                .fatContent(source.getFatContent())
                .measurementDate(source.getMeasurementDate())
                .build();
    }

    GeneralMeasurement updateGeneralMeasurementWithDto(GeneralMeasurement toUpdate, GeneralMeasurementDto source) {
        return toUpdate.toBuilder()
                .id(source.getId())
                .weight(source.getWeight())
                .height(source.getHeight())
                .bmi(calculateBmi(source.getWeight(), source.getHeight()))
                .muscleContent(source.getMuscleContent())
                .fatContent(source.getFatContent())
                .measurementDate(source.getMeasurementDate())
                .build();
    }

    private Float calculateBmi(Float weight, Float height) {
        return weight / (float) Math.pow((height / 100), 2);
    }
}
