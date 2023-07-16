package pl.fitnote.training;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.fitnote.training.port.incoming.NewTrainingPlanPort;

@RestController
@RequiredArgsConstructor
@RequestMapping("/training-plans")
public class TrainingPlanController {

    private final NewTrainingPlanPort newTrainingPlanPort;
}
