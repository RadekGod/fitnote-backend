package pl.fitnote.exercise;

import org.springframework.data.jpa.repository.JpaRepository;

interface ExercisePersistRepository extends JpaRepository<Exercise, Long> {
}
