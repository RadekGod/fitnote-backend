package pl.fitnote.training;

import org.springframework.data.jpa.repository.JpaRepository;

interface TrainingPersistRepository extends JpaRepository<Training, Long> {
}
