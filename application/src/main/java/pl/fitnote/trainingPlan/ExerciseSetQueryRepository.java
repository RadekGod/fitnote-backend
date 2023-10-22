package pl.fitnote.trainingPlan;

import org.springframework.data.jpa.repository.JpaRepository;

interface ExerciseSetQueryRepository extends JpaRepository<ExerciseSet, Long> {

//    @Query(value = "select es from ExerciseSet es join es.trainingPlan tp join es.exercise e join tp.user u " +
//            "where es.exercise.id = :exerciseId and tp.id = :trainingPlanId and u.email = :email")
//    List<ExerciseSet> findAllExerciseSetsOfGivenExerciseForTrainingId(@Param("exerciseId") Long exerciseId, @Param("trainingPlanId") Long trainingPlanId, @Param("email") String email);
//
//
//    @Query(value = "select tp from TrainingPlan tp left join tp.user u where tp.id = :trainingPlanId and u.email = :email")
//    <T> Optional<T> findTrainingPlanForUserByEmail(@Param("trainingPlanId") Long trainingPlanId, @Param("email") String email, Class<T> type);

}
