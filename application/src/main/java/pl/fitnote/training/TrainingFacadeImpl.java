package pl.fitnote.training;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fitnote.commons.UserDetails;
import pl.fitnote.exercise.Exercise;
import pl.fitnote.exercise.ExerciseFacade;
import pl.fitnote.user.User;
import pl.fitnote.user.UserFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
class TrainingFacadeImpl implements TrainingFacade {

    private final TrainingFactory trainingFactory;
    private final ExerciseFacade exerciseFacade;
    private final UserFacade userFacade;
    private final TrainingPersistRepository trainingPersistRepository;
    private final TrainingExerciseSetPersistRepository trainingExerciseSetPersistRepository;
    private final TrainingQueryRepository trainingQueryRepository;

    @Override
    @Transactional
    public Long createTraining(final TrainingDto command, final UserDetails userDetails) {
        User requestingUser = userFacade.getUser(userDetails, User.class);
        List<TrainingExercise> trainingExercises = command.getTrainingExercises().stream()
                .map(trainingExerciseDto -> {
                    Exercise exercise = exerciseFacade.getExercise(trainingExerciseDto.getExercise().getId(), userDetails, Exercise.class);
                    List<TrainingExerciseSet> trainingExerciseSets = trainingExerciseDto.getExerciseSets().stream()
                            .map(trainingFactory::createTrainingExerciseSet)
                            .toList();

                    TrainingExercise trainingExercise = trainingFactory.createTrainingExercise(trainingExerciseDto, exercise, trainingExerciseSets);
                    trainingExercise.getExerciseSets().forEach(trainingExerciseSet -> trainingExerciseSet.setTrainingExercise(trainingExercise));
                    return trainingExercise;
                }).toList();

        Training trainingToSave = trainingFactory.createTrainingFromDto(command, trainingExercises, requestingUser);
        trainingToSave.getTrainingExercises().forEach(trainingExercise -> trainingExercise.setTraining(trainingToSave));
        return trainingPersistRepository.save(trainingToSave).getId();
    }

    @Override
    @Transactional
    public void updateTraining(final Long trainingId, final TrainingDto command, final UserDetails userDetails) {
        Training trainingToUpdate = trainingQueryRepository.findByIdAndUserEmail(trainingId, userDetails.getEmail(), Training.class)
                .orElseThrow(EntityNotFoundException::new);
        trainingToUpdate = trainingToUpdate.toBuilder()
                .name(command.getName())
                .note(command.getNote())
                .startTime(command.getStartTime())
                .finishTime(command.getFinishTime())
                .build();

        trainingToUpdate.getTrainingExercises().forEach(trainingExercise -> {
            TrainingExerciseDto trainingExerciseDtoToUpdate = command.getTrainingExercises().stream()
                    .filter(trainingExerciseDto -> Objects.equals(trainingExerciseDto.getId(), trainingExercise.getId()))
                    .findFirst()
                    .orElseThrow(EntityNotFoundException::new);

            trainingExercise.setNote(trainingExerciseDtoToUpdate.getNote());
            List<TrainingExerciseSet> trainingExerciseSetsToSave;

            if (trainingExerciseDtoToUpdate.getExerciseSets().size() >= trainingExercise.getExerciseSets().size()) {
                trainingExerciseSetsToSave = updateAndCreateAdditionalExerciseSets(trainingExercise, trainingExerciseDtoToUpdate);
            } else {
                trainingExerciseSetsToSave = updateAndDeleteExcessExerciseSets(trainingExercise, trainingExerciseDtoToUpdate);
            }

            trainingExercise.setExerciseSets(trainingExerciseSetsToSave);
        });
        trainingPersistRepository.save(trainingToUpdate);
    }

    private List<TrainingExerciseSet> updateAndCreateAdditionalExerciseSets(TrainingExercise trainingExercise, TrainingExerciseDto sourceTrainingExerciseDto) {
        List<TrainingExerciseSet> trainingExerciseSetsToReturn = new ArrayList<>();
        for (int i = 0; i < sourceTrainingExerciseDto.getExerciseSets().size(); i++) {
            if (i < trainingExercise.getExerciseSets().size()) {
                trainingExerciseSetsToReturn.add(trainingFactory.updateTrainingExerciseSet(trainingExercise.getExerciseSets().get(i), sourceTrainingExerciseDto.getExerciseSets().get(i)));
            } else {
                trainingExerciseSetsToReturn.add(trainingFactory.createTrainingExerciseSetForExistingTrainingExercise(sourceTrainingExerciseDto.getExerciseSets().get(i), trainingExercise));
            }
        }
        return trainingExerciseSetsToReturn;
    }

    private List<TrainingExerciseSet> updateAndDeleteExcessExerciseSets(TrainingExercise trainingExercise, TrainingExerciseDto sourceTrainingExerciseDto) {
        List<TrainingExerciseSet> trainingExerciseSetsToReturn = new ArrayList<>();
        for (int i = 0; i < sourceTrainingExerciseDto.getExerciseSets().size(); i++) {
            trainingExerciseSetsToReturn.add(trainingFactory.updateTrainingExerciseSet(trainingExercise.getExerciseSets().get(i), sourceTrainingExerciseDto.getExerciseSets().get(i)));
        }
        List<TrainingExerciseSet> trainingExerciseSetsToDelete = trainingExercise.getExerciseSets().subList(sourceTrainingExerciseDto.getExerciseSets().size(), trainingExercise.getExerciseSets().size());
        trainingExerciseSetPersistRepository.deleteAll(trainingExerciseSetsToDelete);
        return trainingExerciseSetsToReturn;
    }

    @Override
    public <T> T getTraining(final Long trainingId, final UserDetails userDetails, final Class<T> type) {
        return trainingQueryRepository.findByIdAndUserEmail(trainingId, userDetails.getEmail(), type)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<TrainingProjection> getAllTrainings(final UserDetails userDetails) {
        return trainingQueryRepository.findAllByUserEmail(userDetails.getEmail());
    }

    @Override
    @Transactional
    public void deleteTraining(final Long trainingId, final UserDetails userDetails) {
        Training training = trainingQueryRepository.findByIdAndUserEmail(trainingId, userDetails.getEmail(), Training.class)
                .orElseThrow(EntityNotFoundException::new);
        trainingPersistRepository.delete(training);
    }
}
