package pl.fitnote.exercise;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fitnote.commons.UserDetails;
import pl.fitnote.exercise.dto.ExerciseProjection;
import pl.fitnote.user.User;
import pl.fitnote.user.UserFacade;

import java.util.List;

@Service
@RequiredArgsConstructor
class ExerciseFacadeImpl implements ExerciseFacade {

    private final ExerciseFactory exerciseFactory;
    private final UserFacade userFacade;
    private final ExercisePersistRepository exercisePersistRepository;
    private final ExerciseQueryRepository exerciseQueryRepository;

    @Override
    @Transactional
    public Long createExercise(final ExerciseDto command, final UserDetails userDetails) {
        Exercise toCreate = exerciseFactory.createExerciseFromDto(command);
        toCreate.setUser(userFacade.getUser(userDetails, User.class));
        return exercisePersistRepository.save(toCreate).getId();
    }

    @Override
    public List<ExerciseProjection> getAllExercises(final UserDetails userDetails) {
        return exerciseQueryRepository.findAllExercisesForUserByKeycloakId(userDetails.getKeycloakId());
    }

    @Override
    public <T> T getExercise(final Long exerciseId, final UserDetails userDetails, Class<T> type) {
        return exerciseQueryRepository.findExerciseForUserByKeycloakId(exerciseId, userDetails.getKeycloakId(), type)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public void updateExercise(final Long exerciseId, final ExerciseDto command, final UserDetails userDetails) {
        Exercise toUpdate = exerciseQueryRepository.findExerciseForUserByKeycloakId(exerciseId, userDetails.getKeycloakId(), Exercise.class)
                .orElseThrow(EntityNotFoundException::new);
        toUpdate.setName(command.getName());
        toUpdate.setDescription(command.getDescription());
        toUpdate.setMainMuscles(command.getMainMuscles());
        toUpdate.setSupportiveMuscles(command.getSupportiveMuscles());
        toUpdate.setExerciseType(command.getExerciseType());
        toUpdate.setExerciseCategoryGroups(command.getExerciseCategoryGroups());
        exercisePersistRepository.save(toUpdate);
    }

    @Override
    @Transactional
    public void deleteExercise(final Long exerciseId, final UserDetails userDetails) {
        Exercise toDelete = exerciseQueryRepository.findExerciseForUserByKeycloakId(exerciseId, userDetails.getKeycloakId(), Exercise.class)
                .orElseThrow(EntityNotFoundException::new);
        if (toDelete.getUser() != null) {
            exercisePersistRepository.delete(toDelete);
        } else {
            throw new EntityNotFoundException();
        }

    }
}
