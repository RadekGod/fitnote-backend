package pl.fitnote.body.measurements;

import org.springframework.stereotype.Service;
import pl.fitnote.commons.MeasureUnitsCalculator;
import pl.fitnote.user.LengthUnit;
import pl.fitnote.user.User;
import pl.fitnote.user.WeightUnit;

@Service
class GeneralMeasurementFactory {
    private final MeasureUnitsCalculator measureUnitsCalculator = new MeasureUnitsCalculator();

    GeneralMeasurement createGeneralMeasurementFromDto(GeneralMeasurementDto source) {
        return GeneralMeasurement.builder()
                .id(source.getId())
                .weight(source.getWeight())
                .height(source.getHeight())
                .bmi(calculateBmi(source))
                .muscleContent(source.getMuscleContent())
                .bodyFat(source.getBodyFat())
                .lengthUnit(source.getLengthUnit())
                .weightUnit(source.getWeightUnit())
                .measurementDate(source.getMeasurementDate())
                .build();
    }

    GeneralMeasurement updateGeneralMeasurementWithDto(GeneralMeasurement toUpdate, GeneralMeasurementDto source) {
        return toUpdate.toBuilder()
                .id(source.getId())
                .weight(source.getWeight())
                .height(source.getHeight())
                .bmi(calculateBmi(source))
                .muscleContent(source.getMuscleContent())
                .bodyFat(source.getBodyFat())
                .lengthUnit(source.getLengthUnit())
                .weightUnit(source.getWeightUnit())
                .measurementDate(source.getMeasurementDate())
                .build();
    }

    private Float calculateBmi(GeneralMeasurementDto generalMeasurementDto) {
        Float weight = generalMeasurementDto.getWeightUnit().equals(WeightUnit.KILOGRAM)
                ? generalMeasurementDto.getWeight()
                : measureUnitsCalculator.recalculateWeightValue(WeightUnit.KILOGRAM, generalMeasurementDto.getWeight());
        Float height = generalMeasurementDto.getLengthUnit().equals(LengthUnit.CENTIMETER)
                ? generalMeasurementDto.getHeight()
                : measureUnitsCalculator.recalculateLengthValue(LengthUnit.CENTIMETER, generalMeasurementDto.getHeight());
        return weight / (float) Math.pow((height / 100), 2);
    }

    GeneralMeasurementDto recalculateMeasurementValuesAndCreateDto(User user, GeneralMeasurementProjection source) {
        GeneralMeasurementDto createdDto = GeneralMeasurementDto.builder()
                .id(source.getId())
                .height(source.getHeight())
                .weight(source.getWeight())
                .bmi(source.getBmi())
                .muscleContent(source.getMuscleContent())
                .bodyFat(source.getBodyFat())
                .weightUnit(source.getWeightUnit())
                .lengthUnit(source.getLengthUnit())
                .measurementDate(source.getMeasurementDate())
                .build();

        if (!user.getUserSettings().getWeightUnit().equals(source.getWeightUnit())) {
            createdDto = createdDto.toBuilder()
                    .weight(measureUnitsCalculator.recalculateWeightValue(user.getUserSettings().getWeightUnit(), source.getWeight()))
                    .weightUnit(user.getUserSettings().getWeightUnit())
                    .build();
        }

        if (!user.getUserSettings().getLengthUnit().equals(source.getLengthUnit())) {
            createdDto = createdDto.toBuilder()
                    .height(measureUnitsCalculator.recalculateLengthValue(user.getUserSettings().getLengthUnit(), source.getWeight()))
                    .lengthUnit(user.getUserSettings().getLengthUnit())
                    .build();
        }
        return createdDto;
    }

    GeneralMeasurementDto createDtoFromProjection(GeneralMeasurementProjection source) {
        return GeneralMeasurementDto.builder()
                .id(source.getId())
                .height(source.getHeight())
                .weight(source.getWeight())
                .bmi(source.getBmi())
                .muscleContent(source.getMuscleContent())
                .bodyFat(source.getBodyFat())
                .weightUnit(source.getWeightUnit())
                .lengthUnit(source.getLengthUnit())
                .measurementDate(source.getMeasurementDate())
                .build();
    }
}
