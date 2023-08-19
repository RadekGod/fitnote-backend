package pl.fitnote.sleep;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.fitnote.user_session_utils.SecurityContextUtils;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sleep")
class SleepController {

    private final SleepFacade sleepFacade;

    @PostMapping()
    ResponseEntity<Long> createSleep(@RequestBody SleepDto command) {
        return new ResponseEntity<>(sleepFacade.createSleep(command, SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<SleepProjection> getSleep(@PathVariable("id") Long sleepId) {
        try {
            return new ResponseEntity<>(sleepFacade.getSleep(sleepId, SecurityContextUtils.getLoggedUserDetails(), SleepProjection.class), HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sleep not found with given id");
        }
    }

    @GetMapping()
    ResponseEntity<List<SleepProjection>> getAllSleeps() {
        return new ResponseEntity<>(sleepFacade.getAllSleeps(SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> updateSleep(@PathVariable("id") Long sleepId, @RequestBody SleepDto command) {
        try {
            sleepFacade.updateSleep(sleepId, command, SecurityContextUtils.getLoggedUserDetails());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sleep not found with given id");
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteSleep(@PathVariable("id") Long sleepId) {
        try {
            sleepFacade.deleteSleep(sleepId, SecurityContextUtils.getLoggedUserDetails());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sleep not found with given id");
        }
    }
}
