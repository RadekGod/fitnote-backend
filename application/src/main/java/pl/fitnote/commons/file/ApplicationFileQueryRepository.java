package pl.fitnote.commons.file;

import org.springframework.data.jpa.repository.JpaRepository;

interface ApplicationFileQueryRepository extends JpaRepository<ApplicationFile, Long> {
}
