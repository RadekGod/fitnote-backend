package pl.fitnote.trainingPlan;

import pl.fitnote.commons.UserDetails;

interface TrainingPlanFacade {

    Long createTrainingPlan(TrainingPlanDto command, UserDetails userDetails);
}
