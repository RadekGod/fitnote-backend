package pl.fitnote.body.measurements.generalMeasurement;

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
@RequestMapping("/general-measurements")
class GeneralMeasurementController {

    private final GeneralMeasurementFacade generalMeasurementFacade;

    @PostMapping()
    ResponseEntity<Long> createGeneralMeasurement(@RequestBody @Valid GeneralMeasurementDto command) {
        return new ResponseEntity<>(generalMeasurementFacade.createGeneralMeasurement(command, SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<GeneralMeasurementProjection> getGeneralMeasurement(@PathVariable("id") Long generalMeasurementId) {
        try {
            return new ResponseEntity<>(generalMeasurementFacade.getGeneralMeasurement(generalMeasurementId, SecurityContextUtils.getLoggedUserDetails(), GeneralMeasurementProjection.class), HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Measurement not found with given id");
        }
    }

    @GetMapping("/latest")
    ResponseEntity<GeneralMeasurementDto> getLatestGeneralMeasurement() {
        try {
            return new ResponseEntity<>(generalMeasurementFacade.getUsersLatestGeneralMeasurement(SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Measurements found");
        }
    }

    @GetMapping()
    ResponseEntity<List<GeneralMeasurementProjection>> getAllGeneralMeasurements() {
        return new ResponseEntity<>(generalMeasurementFacade.getAllGeneralMeasurements(SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> updateGeneralMeasurement(@PathVariable("id") Long generalMeasurementId, @RequestBody @Valid GeneralMeasurementDto command) {
        try {
            generalMeasurementFacade.updateGeneralMeasurement(generalMeasurementId, command, SecurityContextUtils.getLoggedUserDetails());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Measurement not found with given id");
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteGeneralMeasurement(@PathVariable("id") Long generalMeasurementId) {
        try {
            generalMeasurementFacade.deleteGeneralMeasurement(generalMeasurementId, SecurityContextUtils.getLoggedUserDetails());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Measurement not found with given id");
        }
    }
}
