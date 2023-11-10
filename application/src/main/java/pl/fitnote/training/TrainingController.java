package pl.fitnote.training;


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
import pl.fitnote.commons.userSessionUtils.SecurityContextUtils;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trainings")
class TrainingController {

    private final TrainingFacade trainingFacade;

    @PostMapping()
    ResponseEntity<Long> createTraining(@RequestBody TrainingDto command) {
        return new ResponseEntity<>(trainingFacade.createTraining(command, SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> updateTraining(@PathVariable("id") Long trainingId, @RequestBody TrainingDto command) {
        try {
            trainingFacade.updateTraining(trainingId, command, SecurityContextUtils.getLoggedUserDetails());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Training not found with given id");
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<TrainingDto> getTraining(@PathVariable("id") Long trainingId) {
        try {
            return new ResponseEntity<>(trainingFacade.getTraining(trainingId, SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Training not found with given id");
        }
    }

    @GetMapping()
    ResponseEntity<List<TrainingDto>> getAllTrainings() {
        try {
            return new ResponseEntity<>(trainingFacade.getAllTrainings(SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Trainings found");
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTraining(@PathVariable("id") Long trainingId) {
        try {
            trainingFacade.deleteTraining(trainingId, SecurityContextUtils.getLoggedUserDetails());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Training not found with given id");
        }
    }
}
