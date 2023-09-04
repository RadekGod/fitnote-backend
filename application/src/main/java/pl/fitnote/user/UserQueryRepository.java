package pl.fitnote.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserQueryRepository extends JpaRepository<User, Long> {

    <T> Optional<T> findUserByEmail(String email, Class<T> type);
    Boolean existsByEmail(String email);
}