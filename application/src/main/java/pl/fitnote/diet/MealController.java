package pl.fitnote.diet;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.fitnote.commons.userSessionUtils.SecurityContextUtils;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/meals")
class MealController {

    private final MealFacade mealFacade;

    @PostMapping()
    ResponseEntity<Long> createMeal(@RequestBody MealDto command) {
        return new ResponseEntity<>(mealFacade.createMeal(command, SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<MealProjection> getMeal(@PathVariable("id") Long mealId) {
        try {
            return new ResponseEntity<>(mealFacade.getMeal(mealId, SecurityContextUtils.getLoggedUserDetails(), MealProjection.class), HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Meal not found with given id");
        }
    }

    @GetMapping()
    ResponseEntity<List<MealProjection>> getAllTodayMeals() {
        return new ResponseEntity<>(mealFacade.getAllTodayMeals(SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> updateMeal(@PathVariable("id") Long mealId, @RequestBody MealDto command) {
        try {
            mealFacade.updateMeal(mealId, command, SecurityContextUtils.getLoggedUserDetails());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Meal not found with given id");
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteActivity(@PathVariable("id") Long mealId) {
        try {
            mealFacade.deleteMeal(mealId, SecurityContextUtils.getLoggedUserDetails());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Meal not found with given id");
        }
    }
}
