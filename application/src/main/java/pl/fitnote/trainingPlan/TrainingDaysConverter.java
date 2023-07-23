package pl.fitnote.trainingPlan;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
class TrainingDaysConverter implements AttributeConverter<List<TrainingDay>, String> {
    @Override
    public String convertToDatabaseColumn(final List<TrainingDay> trainingDays) {
        if (trainingDays == null) {
            return null;
        }
        return trainingDays.stream()
                .map(Enum::name)
                .collect(Collectors.joining(", "));
    }

    @Override
    public List<TrainingDay> convertToEntityAttribute(final String string) {
        if (string == null || string.isBlank()) {
            return null;
        }
        return Arrays.stream(string.split(", ")).map(TrainingDay::valueOf).toList();
    }
}
