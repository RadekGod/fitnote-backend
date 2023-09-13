package pl.fitnote.body;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.fitnote.user_session_utils.SecurityContextUtils;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/general-measurements")
class GeneralMeasurementsController {

    private final BodyFacade bodyFacade;

    @PostMapping()
    ResponseEntity<Long> createGeneralMeasurement(@RequestBody GeneralMeasurementDto command) {
        return new ResponseEntity<>(bodyFacade.createGeneralMeasurement(command, SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<GeneralMeasurementProjection> getGeneralMeasurement(@PathVariable("id") Long generalMeasurementId) {
        try {
            return new ResponseEntity<>(bodyFacade.getGeneralMeasurement(generalMeasurementId, SecurityContextUtils.getLoggedUserDetails(), GeneralMeasurementProjection.class), HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Measurement not found with given id");
        }
    }

    @GetMapping("/latest")
    ResponseEntity<GeneralMeasurementProjection> getLatestGeneralMeasurement() {
        try {
            return new ResponseEntity<>(bodyFacade.getUsersLatestGeneralMeasurement(SecurityContextUtils.getLoggedUserDetails(), GeneralMeasurementProjection.class), HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Measurements found");
        }
    }

    @GetMapping()
    ResponseEntity<List<GeneralMeasurementProjection>> getAllGeneralMeasurements() {
        return new ResponseEntity<>(bodyFacade.getAllGeneralMeasurements(SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> updateGeneralMeasurement(@PathVariable("id") Long generalMeasurementId, @RequestBody GeneralMeasurementDto command) {
        try {
            bodyFacade.updateGeneralMeasurement(generalMeasurementId, command, SecurityContextUtils.getLoggedUserDetails());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Measurement not found with given id");
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteGeneralMeasurement(@PathVariable("id") Long generalMeasurementId) {
        try {
            bodyFacade.deleteGeneralMeasurement(generalMeasurementId, SecurityContextUtils.getLoggedUserDetails());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Measurement not found with given id");
        }
    }
}
