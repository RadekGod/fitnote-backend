package pl.fitnote.exercise;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Converter
class ExerciseCategoryGroupConverter implements AttributeConverter<List<ExerciseCategoryGroupEnum>, String> {
    @Override
    public String convertToDatabaseColumn(final List<ExerciseCategoryGroupEnum> exerciseCategoryGroupEnums) {
            if (exerciseCategoryGroupEnums == null) {
            return null;
        }
        return exerciseCategoryGroupEnums.stream()
                .filter(Objects::nonNull)
                .map(Enum::name)
                .collect(Collectors.joining(", "));
    }

    @Override
    public List<ExerciseCategoryGroupEnum> convertToEntityAttribute(final String string) {
        if (string == null || string.isBlank()) {
            return null;
        }
        return Arrays.stream(string.split(", ")).map(ExerciseCategoryGroupEnum::valueOf).toList();
    }
}
