package pl.fitnote.trainingPlan;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.fitnote.commons.userSessionUtils.SecurityContextUtils;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/training-plans")
class TrainingPlanController {

    private final TrainingPlanFacade trainingPlanFacade;
    private final TrainingPlanExerciseFacade trainingPlanExerciseFacade;

    @PostMapping()
    ResponseEntity<Long> createTrainingPlan(@RequestBody CreateTrainingPlanDto command) {
        return new ResponseEntity<>(trainingPlanFacade.createTrainingPlan(command, SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> updateTrainingPlan(@PathVariable("id") Long trainingPlanId, @RequestBody TrainingPlanDto command) {
        trainingPlanFacade.updateTrainingPlan(trainingPlanId, command, SecurityContextUtils.getLoggedUserDetails());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<TrainingPlanDto> getTrainingPlan(@PathVariable("id") Long trainingPlanId) {
        try {
            return new ResponseEntity<>(trainingPlanFacade.getTrainingPlan(trainingPlanId, SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Training Plan not found with given id");
        }
    }

    @GetMapping()
    ResponseEntity<List<TrainingPlanDto>> getAllTrainingPlans() {
        try {
            return new ResponseEntity<>(trainingPlanFacade.getAllTrainingPlans(SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Training Plans found");
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTrainingPlan(@PathVariable("id") Long trainingPlanId) {
        trainingPlanFacade.deleteTrainingPlan(trainingPlanId, SecurityContextUtils.getLoggedUserDetails());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/{trainingPlanId}/exercises/{trainingPlanExerciseId}")
    ResponseEntity<SimpleTrainingPlanExerciseProjection> getTrainingPlanExercise(@PathVariable("trainingPlanId") Long trainingPlanId, @PathVariable("trainingPlanExerciseId") Long trainingPlanExerciseId) {
        return new ResponseEntity<>(trainingPlanExerciseFacade.getTrainingPlanExercise(trainingPlanId, trainingPlanExerciseId, SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
    }

    @GetMapping("/{id}/exercises")
    ResponseEntity<List<TrainingPlanExerciseProjection>> getAllExercisesFromTrainingPlan(@PathVariable("id") Long trainingPlanId) {
        return new ResponseEntity<>(trainingPlanExerciseFacade.getAllExercisesFromTrainingPlan(trainingPlanId, SecurityContextUtils.getLoggedUserDetails(), TrainingPlanExerciseProjection.class), HttpStatus.OK);
    }

    @PostMapping("/{trainingPlanId}/exercises/{exerciseId}")
    ResponseEntity<Void> addExerciseToTrainingPlan(@PathVariable("trainingPlanId") Long trainingPlanId, @PathVariable("exerciseId") Long exerciseId, @RequestBody TrainingPlanExerciseDto command) {
        trainingPlanExerciseFacade.addExerciseToTrainingPlan(trainingPlanId, exerciseId, command, SecurityContextUtils.getLoggedUserDetails());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{trainingPlanId}/exercises/{trainingPlanExerciseId}")
    ResponseEntity<Void> updateTrainingPlanExercise(@PathVariable("trainingPlanId") Long trainingPlanId, @PathVariable("trainingPlanExerciseId") Long trainingPlanExerciseId, @RequestBody TrainingPlanExerciseDto command) {
        trainingPlanExerciseFacade.updateTrainingPlanExercise(trainingPlanId, trainingPlanExerciseId, command, SecurityContextUtils.getLoggedUserDetails());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/exercises")
    ResponseEntity<Void> deleteExerciseFromTrainingPlan(@PathVariable("id") Long trainingPlanId, @RequestParam("trainingPlanExerciseId") Long trainingPlanExerciseId) {
        trainingPlanExerciseFacade.deleteExerciseFromTrainingPlan(trainingPlanId, trainingPlanExerciseId, SecurityContextUtils.getLoggedUserDetails());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
