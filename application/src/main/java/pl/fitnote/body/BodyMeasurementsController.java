package pl.fitnote.body;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping("/body-measurements")
class BodyMeasurementsController {

    private final BodyFacade bodyFacade;

    @PostMapping()
    ResponseEntity<Long> createBodyMeasurement(Authentication authentication, @RequestBody BodyMeasurementDto command) {
        return new ResponseEntity<>(bodyFacade.createBodyMeasurement(command, authentication.getName()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<BodyMeasurementProjection> getBodyMeasurement(@PathVariable("id") Long bodyMeasurementId) {
        try {
            return new ResponseEntity<>(bodyFacade.getBodyMeasurement(bodyMeasurementId, SecurityContextUtils.getLoggedUserDetails(), BodyMeasurementProjection.class), HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Measurement not found with given id");
        }
    }

    @GetMapping("/latest")
    ResponseEntity<BodyMeasurementProjection> getLatestBodyMeasurement(Authentication authentication) {
        var temp = SecurityContextHolder.getContext();
//        SecurityContextUtils.getLoggedUserDetails();
        try {
            return new ResponseEntity<>(bodyFacade.getUsersLatestBodyMeasurement(authentication.getName(), BodyMeasurementProjection.class), HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Measurements found");
        }
    }

    @GetMapping()
    ResponseEntity<List<BodyMeasurementProjection>> getAllBodyMeasurements(Authentication authentication) {
        var temp1 = authentication.getName();
        var temp = bodyFacade.getAllBodyMeasurements(authentication.getName());
        return new ResponseEntity<>(bodyFacade.getAllBodyMeasurements(authentication.getName()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> updateBodyMeasurement(@PathVariable("id") Long bodyMeasurementId, @RequestBody BodyMeasurementDto command) {
        try {
            bodyFacade.updateBodyMeasurement(bodyMeasurementId, command, SecurityContextUtils.getLoggedUserDetails());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Measurement not found with given id");
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteBodyMeasurement(@PathVariable("id") Long bodyMeasurementId) {
        try {
            bodyFacade.deleteBodyMeasurement(bodyMeasurementId, SecurityContextUtils.getLoggedUserDetails());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Measurement not found with given id");
        }
    }
}
