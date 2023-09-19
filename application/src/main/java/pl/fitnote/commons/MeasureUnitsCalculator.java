package pl.fitnote.commons;

import pl.fitnote.user.LengthUnit;
import pl.fitnote.user.WeightUnit;

import java.util.Objects;

public class MeasureUnitsCalculator {

    public Float recalculateLengthValue(LengthUnit desiredLengthUnit, Float measureValueFrom) {
        if (Objects.isNull(measureValueFrom)) {
            return null;
        }
        if (desiredLengthUnit.equals(LengthUnit.CENTIMETER)) {
            return  measureValueFrom * 2.54F;
        } else {
            return  measureValueFrom / 2.54F;
        }
    }

    public Float recalculateWeightValue(WeightUnit desiredWeightUnit, Float measureValueFrom) {
        if (Objects.isNull(measureValueFrom)) {
            return null;
        }
        if (desiredWeightUnit.equals(WeightUnit.KILOGRAM)) {
            return measureValueFrom * 0.45359237F;
        } else {
            return measureValueFrom / 0.45359237F;
        }
    }
}
