package pl.fitnote.activity;

import org.springframework.data.jpa.repository.JpaRepository;

interface ActivityPersistRepository extends JpaRepository<Activity, Long> {
}
