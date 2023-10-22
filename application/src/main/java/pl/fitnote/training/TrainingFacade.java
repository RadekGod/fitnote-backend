package pl.fitnote.training;

import pl.fitnote.commons.UserDetails;

import java.util.List;

interface TrainingFacade {
    Long createTraining(TrainingDto command, UserDetails userDetails);

    void updateTraining(Long trainingId, TrainingDto command, UserDetails userDetails);

    <T> T getTraining(Long trainingId, UserDetails userDetails, Class<T> type);

    List<TrainingProjection> getAllTrainings(UserDetails userDetails);

    void deleteTraining(final Long trainingId, final UserDetails userDetails);
}
