package pl.fitnote.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface UserQueryRepository extends JpaRepository<User, Long> {

    <T> Optional<T> findByKeycloakId(String keycloakId, Class<T> type);
}