package pl.fitnote.trainingPlan;

import org.springframework.data.jpa.repository.JpaRepository;

interface TrainingPlanExercisePersistRepository extends JpaRepository<TrainingPlanExercise, Long> {
}
