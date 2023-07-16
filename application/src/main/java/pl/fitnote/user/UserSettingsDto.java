package pl.fitnote.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class UserSettingsDto {
    private WeightUnit weightUnit;
    private LengthUnit lengthUnit;
}
