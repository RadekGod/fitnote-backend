package pl.fitnote.user;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserSettingsQueryRepository extends JpaRepository<UserSettings, Long> {
}
