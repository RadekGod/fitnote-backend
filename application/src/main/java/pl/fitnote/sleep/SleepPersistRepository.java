package pl.fitnote.sleep;

import org.springframework.data.jpa.repository.JpaRepository;

interface SleepPersistRepository extends JpaRepository<Sleep, Long> {
}
