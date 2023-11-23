package pl.fitnote.body.measurements.bodyMasurement;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.fitnote.commons.userSessionUtils.SecurityContextUtils;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/body-measurements")
class BodyMeasurementController {

    private final BodyMeasurementFacade bodyMeasurementFacade;

    @PostMapping()
    ResponseEntity<Long> createBodyMeasurement(@RequestBody @Valid BodyMeasurementDto command) {
        return new ResponseEntity<>(bodyMeasurementFacade.createBodyMeasurement(command, SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<BodyMeasurementProjection> getBodyMeasurement(@PathVariable("id") Long bodyMeasurementId) {
        try {
            return new ResponseEntity<>(bodyMeasurementFacade.getBodyMeasurement(bodyMeasurementId, SecurityContextUtils.getLoggedUserDetails(), BodyMeasurementProjection.class), HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Measurement not found with given id");
        }
    }

    @GetMapping("/latest")
    ResponseEntity<?> getLatestBodyMeasurement() {
        try {
            return new ResponseEntity<>(bodyMeasurementFacade.getUsersLatestBodyMeasurement(SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            return new ResponseEntity<>("No Measurements found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    ResponseEntity<List<BodyMeasurementProjection>> getAllBodyMeasurements() {
        return new ResponseEntity<>(bodyMeasurementFacade.getAllBodyMeasurements(SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> updateBodyMeasurement(@PathVariable("id") Long bodyMeasurementId, @RequestBody @Valid BodyMeasurementDto command) {
        try {
            bodyMeasurementFacade.updateBodyMeasurement(bodyMeasurementId, command, SecurityContextUtils.getLoggedUserDetails());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Measurement not found with given id");
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteBodyMeasurement(@PathVariable("id") Long bodyMeasurementId) {
        try {
            bodyMeasurementFacade.deleteBodyMeasurement(bodyMeasurementId, SecurityContextUtils.getLoggedUserDetails());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Measurement not found with given id");
        }
    }
}
