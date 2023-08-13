package pl.fitnote.user;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserPersistRepository extends JpaRepository<User, Long> {


}
