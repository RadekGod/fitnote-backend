package pl.fitnote.user;

import org.springframework.stereotype.Service;
import pl.fitnote.user.dto.UserSettingsDto;

@Service
class UserSettingsFactory {

    UserSettings fromDto(UserSettingsDto source) {
        return UserSettings.builder()
                .weightUnit(source.getWeightUnit())
                .lengthUnit(source.getLengthUnit())
                .build();
    }

    UserSettings createDefaultSettingsForNewUser() {
        return UserSettings.builder()
                .weightUnit(WeightUnit.KILOGRAM)
                .lengthUnit(LengthUnit.CENTIMETER)
                .build();
    }
}
