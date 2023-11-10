package pl.fitnote.training;

import pl.fitnote.commons.UserDetails;

import java.util.List;

interface TrainingFacade {
    Long createTraining(TrainingDto command, UserDetails userDetails);

    void updateTraining(Long trainingId, TrainingDto command, UserDetails userDetails);

    TrainingDto getTraining(Long trainingId, UserDetails userDetails);

    List<TrainingDto> getAllTrainings(UserDetails userDetails);

    void deleteTraining(final Long trainingId, final UserDetails userDetails);
}
