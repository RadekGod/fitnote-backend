package pl.fitnote.exercise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

interface ExercisePersistRepository extends JpaRepository<Exercise, Long> {
//    @Query(nativeQuery = true,
//            value = "delete from training.exercise e where id = (select id from training.exercise e left join user_management.user_details ud on e.user_id = ud.id " +
//            "where e.id = :exerciseId and ud.keycloak_id = :keycloakId")

    @Modifying
    @Query(value = "delete from Exercise e where e.id = :exerciseId and e.user.keycloakId = :keycloakId")
    void deleteByIdAndUserKeycloakId(@Param("exerciseId") Long exerciseId, @Param("keycloakId") String keycloakId);

}
