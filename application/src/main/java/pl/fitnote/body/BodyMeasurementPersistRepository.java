package pl.fitnote.body;

import org.springframework.data.jpa.repository.JpaRepository;

interface BodyMeasurementPersistRepository extends JpaRepository<BodyMeasurement, Long> {
}
