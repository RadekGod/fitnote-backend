package pl.fitnote.activity;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.fitnote.commons.user_session_utils.SecurityContextUtils;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/activities")
class ActivityController {

    private final ActivityFacade activityFacade;

    @PostMapping()
    ResponseEntity<Long> createActivity(@RequestBody ActivityDto command) {
        return new ResponseEntity<>(activityFacade.createActivity(command, SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<ActivityProjection> getActivity(@PathVariable("id") Long activityId) {
        try {
            return new ResponseEntity<>(activityFacade.getActivity(activityId, SecurityContextUtils.getLoggedUserDetails(), ActivityProjection.class), HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity not found with given id");
        }
    }

    @GetMapping()
    ResponseEntity<List<ActivityProjection>> getAllActivities() {
        return new ResponseEntity<>(activityFacade.getAllActivities(SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> updateActivity(@PathVariable("id") Long activityId, @RequestBody ActivityDto command) {
        try {
            activityFacade.updateActivity(activityId, command, SecurityContextUtils.getLoggedUserDetails());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity not found with given id");
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteActivity(@PathVariable("id") Long activityId) {
        try {
            activityFacade.deleteActivity(activityId, SecurityContextUtils.getLoggedUserDetails());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity not found with given id");
        }
    }
}
