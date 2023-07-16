package pl.fitnote.training.model;

import pl.fitnote.training.model.vo.ExerciseSetId;
import pl.fitnote.training.model.vo.ExerciseSetNote;
import pl.fitnote.training.model.vo.ExerciseSetRepetitions;
import pl.fitnote.training.model.vo.ExerciseSetWeight;

public class ExerciseSet {
    private ExerciseSetId id;
    private Exercise exercise;
    private ExerciseSetWeight weight;
    private ExerciseSetRepetitions repetitions;
    private ExerciseSetNote note;
}
