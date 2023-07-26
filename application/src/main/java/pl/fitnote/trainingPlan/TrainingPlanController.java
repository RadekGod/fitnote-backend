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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.fitnote.exerciseSet.ExerciseSetDto;
import pl.fitnote.exerciseSet.ExerciseSetFacade;
import pl.fitnote.user_session_utils.SecurityContextUtils;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/training-plans")
class TrainingPlanController {

    private final TrainingPlanFacade trainingPlanFacade;
    private final ExerciseSetFacade exerciseSetFacade;

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
    ResponseEntity<TrainingPlanProjection> getTrainingPlan(@PathVariable("id") Long trainingPlanId) {
        try {
            return new ResponseEntity<>(trainingPlanFacade.getTrainingPlan(trainingPlanId, SecurityContextUtils.getLoggedUserDetails(), TrainingPlanProjection.class), HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Training Plan not found with given id");
        }
    }

    @GetMapping()
    ResponseEntity<List<TrainingPlanProjection>> getAllTrainingPlans() {
        try {
            return new ResponseEntity<>(trainingPlanFacade.getAllTrainingPlans(SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Training Plans found");
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> deleteTrainingPlan(@PathVariable("id") Long trainingPlanId) {
        trainingPlanFacade.deleteTrainingPlan(trainingPlanId, SecurityContextUtils.getLoggedUserDetails());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/{id}/exercise-sets")
    ResponseEntity<Void> addExerciseToTrainingPlan(@PathVariable("id") Long trainingPlanId, @RequestBody List<ExerciseSetDto> command) {
        trainingPlanFacade.addExerciseToTrainingPlan(trainingPlanId, command, SecurityContextUtils.getLoggedUserDetails());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/exercise-sets")
    ResponseEntity<Void> updateExerciseInTrainingPlan(@PathVariable("id") Long trainingPlanId, @RequestBody List<ExerciseSetDto> command) {
        trainingPlanFacade.updateExerciseInTrainingPlan(trainingPlanId, command, SecurityContextUtils.getLoggedUserDetails());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/exercise-sets")
    ResponseEntity<Void> deleteExerciseFromTrainingPlan(@PathVariable("id") Long trainingPlanId, @RequestBody List<ExerciseSetDto> command) {
        trainingPlanFacade.deleteExerciseFromTrainingPlan(trainingPlanId, SecurityContextUtils.getLoggedUserDetails());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
