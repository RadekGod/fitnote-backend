package pl.fitnote.user.dto;

import pl.fitnote.user.LengthUnit;
import pl.fitnote.user.WeightUnit;

public interface UserSettingsProjection {
    WeightUnit getWeightUnit();

    LengthUnit getLengthUnit();
}
