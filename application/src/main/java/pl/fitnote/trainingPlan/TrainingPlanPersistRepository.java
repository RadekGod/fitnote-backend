package pl.fitnote.trainingPlan;

import org.springframework.data.jpa.repository.JpaRepository;

interface TrainingPlanPersistRepository extends JpaRepository<TrainingPlan, Long> {
}
