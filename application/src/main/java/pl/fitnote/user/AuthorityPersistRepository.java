package pl.fitnote.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

interface AuthorityPersistRepository extends JpaRepository<Authority, Long> {
    <T> Optional<T> findByName(String name, Class<T> type);

        @Query("select a from Authority a where a.name in :names")
    Set<Authority> findAllAuthoritiesByName(@Param("names") Set<String> names);

    Set<Authority> findAllByDefaultAuthorityIsTrue();
}
