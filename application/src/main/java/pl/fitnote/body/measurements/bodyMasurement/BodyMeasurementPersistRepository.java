package pl.fitnote.body.measurements.bodyMasurement;

import org.springframework.data.jpa.repository.JpaRepository;

interface BodyMeasurementPersistRepository extends JpaRepository<BodyMeasurement, Long> {
}
