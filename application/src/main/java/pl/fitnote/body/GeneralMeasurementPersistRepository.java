package pl.fitnote.body;

import org.springframework.data.jpa.repository.JpaRepository;

interface GeneralMeasurementPersistRepository  extends JpaRepository<GeneralMeasurement, Long> {
}
