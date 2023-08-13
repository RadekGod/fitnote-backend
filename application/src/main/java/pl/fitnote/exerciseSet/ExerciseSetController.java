package pl.fitnote.exerciseSet;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
@RequestMapping("/exercise-sets")
class ExerciseSetController {

    private final ExerciseSetFacade exerciseSetFacade;
//    @PostMapping("/{id}")
//    ResponseEntity<Void> addExerciseToTrainingPlan(@PathVariable("id") Long trainingPlanId, @RequestBody List<ExerciseSetDto> command) {
//        exerciseSetFacade.addExerciseToTrainingPlan(trainingPlanId, command, SecurityContextUtils.getLoggedUserDetails());
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

//    @GetMapping("/{id}")
//    ResponseEntity<TrainingPlanProjection> getTrainingPlan(@PathVariable("id") Long exerciseId) {
//        try {
//            return new ResponseEntity<>(trainingPlanFacade.getTrainingPlan(exerciseId, SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
//        } catch (EntityNotFoundException exception) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Training Plan not found with given id");
//        }
//    }
//
//    @GetMapping()
//    ResponseEntity<List<TrainingPlanProjection>> getAllTrainingPlans() {
//        try {
//            return new ResponseEntity<>(trainingPlanFacade.getAllTrainingPlans(SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
//        } catch (EntityNotFoundException exception) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Training Plans found");
//        }
//    }
//
//    @PutMapping("/{id}")
//    ResponseEntity<Void> updateTrainingPlan(@PathVariable("id") Long exerciseId, @RequestBody TrainingPlanDto command) {
//        trainingPlanFacade.updateTrainingPlan(exerciseId, command, SecurityContextUtils.getLoggedUserDetails());
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
