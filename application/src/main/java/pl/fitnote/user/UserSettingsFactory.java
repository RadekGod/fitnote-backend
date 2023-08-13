package pl.fitnote.user;

import org.springframework.stereotype.Service;
import pl.fitnote.user.dto.UserSettingsDto;
import pl.fitnote.user.dto.UserSettingsProjection;

@Service
class UserSettingsFactory {

    UserSettings fromDto(UserSettingsDto source) {
        return UserSettings.builder()
                .weightUnit(source.getWeightUnit())
                .lengthUnit(source.getLengthUnit())
                .build();
    }

    UserSettings fromProjection(UserSettingsProjection source) {
        return UserSettings.builder()
                .weightUnit(source.getWeightUnit())
                .lengthUnit(source.getLengthUnit())
                .build();
    }
}
