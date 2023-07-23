package pl.fitnote.exercise;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
class ExerciseCategoryGroupConverter implements AttributeConverter<List<ExerciseCategoryGroup>, String> {
    @Override
    public String convertToDatabaseColumn(final List<ExerciseCategoryGroup> exerciseCategoryGroups) {
        if (exerciseCategoryGroups == null) {
            return null;
        }
        return exerciseCategoryGroups.stream()
                .map(Enum::name)
                .collect(Collectors.joining(", "));
    }

    @Override
    public List<ExerciseCategoryGroup> convertToEntityAttribute(final String string) {
        if (string == null || string.isBlank()) {
            return null;
        }
        return Arrays.stream(string.split(", ")).map(ExerciseCategoryGroup::valueOf).toList();
    }
}
