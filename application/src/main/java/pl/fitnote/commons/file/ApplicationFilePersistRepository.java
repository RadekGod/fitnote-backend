package pl.fitnote.commons.file;

import org.springframework.data.jpa.repository.JpaRepository;

interface ApplicationFilePersistRepository extends JpaRepository<ApplicationFile, Long> {
}
