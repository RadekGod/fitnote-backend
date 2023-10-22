package pl.fitnote.exercise;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Converter
class InvolvedMusclesConverter implements AttributeConverter<List<InvolvedMuscles>, String> {
    @Override
    public String convertToDatabaseColumn(final List<InvolvedMuscles> involvedMuscles) {
        if (involvedMuscles == null) {
            return null;
        }
        return involvedMuscles.stream()
                .filter(Objects::nonNull)
                .map(Enum::name)
                .collect(Collectors.joining(", "));
    }

    @Override
    public List<InvolvedMuscles> convertToEntityAttribute(final String string) {
        if (string == null || string.isBlank()) {
            return null;
        }
        return Arrays.stream(string.split(", ")).map(InvolvedMuscles::valueOf).toList();
    }
}
