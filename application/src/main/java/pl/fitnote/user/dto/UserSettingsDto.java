package pl.fitnote.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.fitnote.user.LengthUnit;
import pl.fitnote.user.WeightUnit;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSettingsDto {
    private WeightUnit weightUnit;
    private LengthUnit lengthUnit;
}
