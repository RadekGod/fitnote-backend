package pl.fitnote.user;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserSettingsQueryRepository extends JpaRepository<UserSettings, Long> {
//    @Query("select us from User.userSettings us where User.keycloakId = :keycloakId")
//    Optional<UserSettings> findByUserKeycloakId(@Param("keycloakId") String keycloakId);
}
