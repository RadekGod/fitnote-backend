package pl.fitnote.exercise;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.fitnote.commons.UserDetails;
import pl.fitnote.commons.file.ApplicationFileFacade;
import pl.fitnote.user.User;
import pl.fitnote.user.UserFacade;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
class ExerciseFacadeImpl implements ExerciseFacade {

    private final ExerciseFactory exerciseFactory;
    private final UserFacade userFacade;
    private final ApplicationFileFacade applicationFileFacade;
    private final ExercisePersistRepository exercisePersistRepository;
    private final ExerciseQueryRepository exerciseQueryRepository;
    private final ExerciseCategoryGroupQueryRepository exerciseCategoryGroupQueryRepository;

    @Override
    @Transactional
    public Long createExercise(final Optional<MultipartFile> image, final ExerciseDto command, final UserDetails userDetails) throws IOException {
        User requestingUser = userFacade.getUser(userDetails.getEmail(), User.class);
        List<ExerciseCategoryGroupEnum> exerciseCategoryGroupEnums = command.getExerciseCategoryGroups();
        exerciseCategoryGroupEnums.add(ExerciseCategoryGroupEnum.CUSTOM);
        Exercise toSave = exerciseFactory.createExerciseFromDto(command);
        toSave.setExerciseCategoryGroups(findAllCategoryGroupsMatchingCommand(exerciseCategoryGroupEnums));
        toSave.setUser(requestingUser);
        if (image.isPresent()) {
            toSave.setApplicationFile(applicationFileFacade.saveFile(image.get()));
        }
        return exercisePersistRepository.save(toSave).getId();
    }


    @Override
    public List<ExerciseProjection> getAllExercises(final UserDetails userDetails) {
        return exerciseQueryRepository.findAllExercisesForUserByEmail(userDetails.getEmail());
    }

    @Override
    @Transactional
    public List<ExerciseProjection> getAllExercisesFromCategory(ExerciseCategoryGroupEnum exerciseCategoryGroupEnum, final UserDetails userDetails) {
        return exerciseQueryRepository.findAllExercisesForUserByEmailAndCategory(userDetails.getEmail(), exerciseCategoryGroupEnum);
    }

    @Override
    @Transactional
    public <T> T getExercise(final Long exerciseId, final UserDetails userDetails, Class<T> type) {
        return exerciseQueryRepository.findExerciseForUserByEmail(exerciseId, userDetails.getEmail(), type)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public void updateExercise(final Long exerciseId, final Optional<MultipartFile> image, final ExerciseDto command, final UserDetails userDetails) throws IOException {
        List<ExerciseCategoryGroupEnum> exerciseCategoryGroupEnums = command.getExerciseCategoryGroups();
        exerciseCategoryGroupEnums.add(ExerciseCategoryGroupEnum.CUSTOM);
        Exercise toUpdate = exerciseQueryRepository.findExerciseForUserByEmail(exerciseId, userDetails.getEmail(), Exercise.class)
                .orElseThrow(EntityNotFoundException::new);
        toUpdate.setName(command.getName());
        toUpdate.setDescription(command.getDescription());
        toUpdate.setMainMuscles(command.getMainMuscles());
        toUpdate.setSupportiveMuscles(command.getSupportiveMuscles());
        toUpdate.setExerciseType(command.getExerciseType());
        toUpdate.setExerciseCategoryGroups(findAllCategoryGroupsMatchingCommand(exerciseCategoryGroupEnums));
        exercisePersistRepository.save(toUpdate);

        if (image.isPresent()) {

            if (Objects.isNull(toUpdate.getApplicationFile())) {
                toUpdate.setApplicationFile(applicationFileFacade.saveFile(image.get()));
            } else {
                applicationFileFacade.updateFile(toUpdate.getApplicationFile().getId(), image.get());
            }
        }
    }

    @Override
    @Transactional
    public void deleteExercise(final Long exerciseId, final UserDetails userDetails) {
        Exercise toDelete = exerciseQueryRepository.findExerciseForUserByEmail(exerciseId, userDetails.getEmail(), Exercise.class)
                .orElseThrow(EntityNotFoundException::new);
        exercisePersistRepository.delete(toDelete);
//        if (Objects.equals(toDelete.getUser().getEmail(), userDetails.getEmail())) {
//            exercisePersistRepository.delete(toDelete);
//        } else {
//            throw new EntityNotFoundException();
//        }
    }

    private Set<ExerciseCategoryGroup> findAllCategoryGroupsMatchingCommand(List<ExerciseCategoryGroupEnum> exerciseCategoryGroupEnums) {
        return exerciseCategoryGroupQueryRepository.findAllByCategoryNameIsIn(exerciseCategoryGroupEnums);
    }
}
