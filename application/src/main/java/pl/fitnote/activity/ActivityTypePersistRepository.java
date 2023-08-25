package pl.fitnote.activity;

import org.springframework.data.jpa.repository.JpaRepository;

interface ActivityTypePersistRepository extends JpaRepository<ActivityType, Long> {
}
