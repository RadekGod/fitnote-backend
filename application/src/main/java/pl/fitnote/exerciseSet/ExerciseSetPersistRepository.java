package pl.fitnote.exerciseSet;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.fitnote.exerciseSet.ExerciseSet;

interface ExerciseSetPersistRepository extends JpaRepository<ExerciseSet, Long> {
}
