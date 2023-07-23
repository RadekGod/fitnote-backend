package pl.fitnote.trainingPlan;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.fitnote.user_session_utils.SecurityContextUtils;

@RestController
@RequiredArgsConstructor
@RequestMapping("/training-plans")
class TrainingPlanController {

    private final TrainingPlanFacade trainingPlanFacade;
    @PostMapping()
    ResponseEntity<Long> createTrainingPlan(@RequestBody TrainingPlanDto command) {
        return new ResponseEntity<>(trainingPlanFacade.createTrainingPlan(command, SecurityContextUtils.getDataForUserCreation()), HttpStatus.OK);
    }
}
