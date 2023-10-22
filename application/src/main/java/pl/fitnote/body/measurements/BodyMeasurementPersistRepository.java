package pl.fitnote.body.measurements;

import org.springframework.data.jpa.repository.JpaRepository;

interface BodyMeasurementPersistRepository extends JpaRepository<BodyMeasurement, Long> {
}
