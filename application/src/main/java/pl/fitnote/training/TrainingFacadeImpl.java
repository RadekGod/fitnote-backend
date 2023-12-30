package pl.fitnote.training;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fitnote.activity.ActivityDto;
import pl.fitnote.activity.ActivityFacade;
import pl.fitnote.activity.ActivityTypeDto;
import pl.fitnote.commons.UserDetails;
import pl.fitnote.exercise.Exercise;
import pl.fitnote.exercise.ExerciseFacade;
import pl.fitnote.user.User;
import pl.fitnote.user.UserFacade;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
class TrainingFacadeImpl implements TrainingFacade {

    private final TrainingFactory trainingFactory;
    private final ExerciseFacade exerciseFacade;
    private final ActivityFacade activityFacade;
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
        Long savedTrainingId = trainingPersistRepository.save(trainingToSave).getId();
        ActivityDto activityDto = ActivityDto.builder()
                .activityDurationInMinutes(calculateTrainingDuration(command.getFinishTime(), command.getStartTime()))
                .trainingPlanName(command.getName())
                .burntKilocalories(0)
                .distanceTraveled(null)
                .activityDate(command.getStartTime())
                .activityType(ActivityTypeDto.builder()
                        .id(7L)
                        .build())
                .build();
        activityFacade.createActivity(activityDto, userDetails);
        return savedTrainingId;
    }

    @Override
    @Transactional
    public void updateTraining(final Long trainingId, final TrainingDto command, final UserDetails userDetails) {
        Training trainingToUpdate = trainingQueryRepository.findByIdAndUserEmail(trainingId, userDetails.getEmail(), Training.class)
                .orElseThrow(EntityNotFoundException::new);
        trainingToUpdate = trainingToUpdate.toBuilder()
                .name(command.getName())
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
    @Transactional
    public TrainingDto getTraining(final Long trainingId, final UserDetails userDetails) {
        SimpleTrainingProjection simpleTrainingProjection = trainingQueryRepository.findByIdAndUserEmail(trainingId, userDetails.getEmail(), SimpleTrainingProjection.class)
                .orElseThrow(EntityNotFoundException::new);
        return trainingFactory.createTrainingDtoFromProjection(simpleTrainingProjection);
    }

    @Override
    @Transactional
    public List<TrainingDto> getAllTrainings(final UserDetails userDetails) {
        List<TrainingDto> trainingDtoList = new ArrayList<>();
        trainingQueryRepository.findAllByUserEmailOrderByStartTimeDesc(userDetails.getEmail()).forEach(trainingProjection -> {
            trainingDtoList.add(trainingFactory.createTrainingDtoFromProjection(trainingProjection));
        });
        return trainingDtoList;
    }

    @Override
    @Transactional
    public void deleteTraining(final Long trainingId, final UserDetails userDetails) {
        Training training = trainingQueryRepository.findByIdAndUserEmail(trainingId, userDetails.getEmail(), Training.class)
                .orElseThrow(EntityNotFoundException::new);
        trainingPersistRepository.delete(training);
    }

    private float calculateTrainingDuration(LocalDateTime finishTime, LocalDateTime startTime) {
        return (float) ((finishTime.toInstant(ZoneId.systemDefault().getRules().getOffset(finishTime)).toEpochMilli()
                - startTime.toInstant(ZoneId.systemDefault().getRules().getOffset(startTime)).toEpochMilli()) / (1000 * 60));
    }
}
