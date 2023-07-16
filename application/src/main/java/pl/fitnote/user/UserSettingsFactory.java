package pl.fitnote.user;

import org.springframework.stereotype.Service;

@Service
class UserSettingsFactory {

    UserSettings fromDto(UserSettingsDto source) {
        return UserSettings.builder()
                .weightUnit(source.getWeightUnit())
                .lengthUnit(source.getLengthUnit())
                .build();
    }
}
