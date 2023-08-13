package pl.fitnote.user;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserSettingsPersistRepository extends JpaRepository<UserSettings, Long> {
}
