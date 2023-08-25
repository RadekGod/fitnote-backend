package pl.fitnote.activity;

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
@RequestMapping("/activity-types")
class ActivityTypeController {

    private final ActivityTypeFacade activityTypeFacade;
    private final ActivityFacade activityFacade;

    @PostMapping()
    ResponseEntity<Long> createActivityType(@RequestBody ActivityTypeDto command) {
        return new ResponseEntity<>(activityTypeFacade.createActivityType(command, SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<ActivityTypeProjection> getActivityType(@PathVariable("id") Long activityTypeId) {
        try {
            return new ResponseEntity<>(activityTypeFacade.getActivityType(activityTypeId, SecurityContextUtils.getLoggedUserDetails(), ActivityTypeProjection.class), HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity type not found with given id");
        }
    }

    @GetMapping()
    ResponseEntity<List<ActivityTypeProjection>> getAllActivityTypes() {
        return new ResponseEntity<>(activityTypeFacade.getAllActivityTypes(SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> updateActivityType(@PathVariable("id") Long activityTypeId, @RequestBody ActivityTypeDto command) {
        try {
            activityFacade.calculateBurntCaloriesIfNeeded(activityTypeId, command, SecurityContextUtils.getLoggedUserDetails());
            activityTypeFacade.updateActivityType(activityTypeId, command, SecurityContextUtils.getLoggedUserDetails());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity type not found with given id");
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteActivityType(@PathVariable("id") Long activityTypeId) {
        try {
            activityTypeFacade.deleteActivityType(activityTypeId, SecurityContextUtils.getLoggedUserDetails());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity type not found with given id");
        }
    }
}
