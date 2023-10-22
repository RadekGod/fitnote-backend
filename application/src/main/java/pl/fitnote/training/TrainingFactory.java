package pl.fitnote.training;

import org.springframework.stereotype.Service;
import pl.fitnote.exercise.Exercise;
import pl.fitnote.user.User;

import java.util.List;

@Service
class TrainingFactory {
    Training createTrainingFromDto(TrainingDto source, List<TrainingExercise> trainingExercises, User requestingUser) {
        return Training.builder()
                .name(source.getName())
                .note(source.getNote())
                .startTime(source.getStartTime())
                .finishTime(source.getFinishTime())
                .trainingExercises(trainingExercises)
                .user(requestingUser)
                .build();
    }

    TrainingExerciseSet createTrainingExerciseSet(TrainingExerciseSetDto source) {
        return TrainingExerciseSet.builder()
                .weight(source.getWeight())
                .repeats(source.getRepeats())
                .completed(source.getCompleted())
                .note(source.getNote())
                .build();
    }

    TrainingExerciseSet createTrainingExerciseSetForExistingTrainingExercise(TrainingExerciseSetDto source, TrainingExercise trainingExercise) {
        return TrainingExerciseSet.builder()
                .weight(source.getWeight())
                .repeats(source.getRepeats())
                .completed(source.getCompleted())
                .note(source.getNote())
                .trainingExercise(trainingExercise)
                .build();
    }

    TrainingExerciseSet updateTrainingExerciseSet(TrainingExerciseSet trainingExerciseSet, TrainingExerciseSetDto source) {
        return trainingExerciseSet.toBuilder()
                .weight(source.getWeight())
                .repeats(source.getRepeats())
                .completed(source.getCompleted())
                .note(source.getNote())
                .build();
    }

    TrainingExercise createTrainingExercise(TrainingExerciseDto source, Exercise exercise, List<TrainingExerciseSet> trainingExerciseSets) {
        return TrainingExercise.builder()
                .measurementUnit(source.getMeasurementUnit())
                .note(source.getNote())
                .exercise(exercise)
                .exerciseSets(trainingExerciseSets)
                .build();
    }
}
