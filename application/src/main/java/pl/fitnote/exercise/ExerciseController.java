package pl.fitnote.exercise;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.fitnote.exercise.dto.ExerciseProjection;
import pl.fitnote.user_session_utils.SecurityContextUtils;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercises")
class ExerciseController {
    private final ExerciseFacade exerciseFacade;

    @PostMapping()
    ResponseEntity<Long> createExercise(@RequestBody ExerciseDto command) {
        return new ResponseEntity<>(exerciseFacade.createExercise(command, SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
    }

    @GetMapping()
    ResponseEntity<List<ExerciseProjection>> getAllExercises() {
        return new ResponseEntity<>(exerciseFacade.getAllExercises(SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
    }

    @GetMapping("/exercise")
    ResponseEntity<ExerciseProjection> getExercise(@RequestParam("id") Long exerciseId) {
        try {
            return new ResponseEntity<>(exerciseFacade.getExercise(exerciseId, SecurityContextUtils.getLoggedUserDetails(), ExerciseProjection.class), HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise not found with given id");
        }
    }

    @PutMapping("/exercise")
    ResponseEntity<Void> updateExercise(@RequestParam("id") Long exerciseId, @RequestBody ExerciseDto command) {
        try {
            exerciseFacade.updateExercise(exerciseId, command, SecurityContextUtils.getLoggedUserDetails());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise not found with given id");
        }
    }

    @DeleteMapping("/exercise")
    ResponseEntity<Void> deleteExercise(@RequestParam("id") Long exerciseId) {
        try {
            exerciseFacade.deleteExercise(exerciseId, SecurityContextUtils.getLoggedUserDetails());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise not found with given id");
        }
    }
}
