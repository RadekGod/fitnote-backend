package pl.fitnote.body.measurements.generalMeasurement;

import org.springframework.data.jpa.repository.JpaRepository;

interface GeneralMeasurementPersistRepository extends JpaRepository<GeneralMeasurement, Long> {
}
