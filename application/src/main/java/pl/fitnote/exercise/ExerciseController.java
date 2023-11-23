package pl.fitnote.exercise;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.type.LogicalType;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import pl.fitnote.commons.userSessionUtils.SecurityContextUtils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercises")
class ExerciseController {
    private final ExerciseFacade exerciseFacade;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<Long> createExercise(@RequestParam(name = "image") Optional<MultipartFile> image, @RequestPart(name = "exerciseData") String exerciseData) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.coercionConfigFor(LogicalType.Array).setCoercion(CoercionInputShape.EmptyArray, CoercionAction.AsEmpty);
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        ExerciseDto exerciseDto = objectMapper.readValue(exerciseData, ExerciseDto.class);

        return new ResponseEntity<>(exerciseFacade.createExercise(image, exerciseDto, SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
    }

    @GetMapping()
    ResponseEntity<List<ExerciseProjection>> getAllExercises() {
        return new ResponseEntity<>(exerciseFacade.getAllExercises(SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
    }

    @GetMapping("/exercise")
    ResponseEntity<SimpleExerciseProjection> getExercise(@RequestParam("id") Long exerciseId) {
        try {
            return new ResponseEntity<>(exerciseFacade.getExercise(exerciseId, SecurityContextUtils.getLoggedUserDetails(), SimpleExerciseProjection.class), HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise not found with given id");
        }
    }

    @GetMapping("/categories")
    ResponseEntity<List<ExerciseProjection>> getAllExercisesFromCategory(@RequestParam("category") ExerciseCategoryGroupEnum category) {
        return new ResponseEntity<>(exerciseFacade.getAllExercisesFromCategory(category, SecurityContextUtils.getLoggedUserDetails()), HttpStatus.OK);
    }

    @PutMapping(name = "/exercise", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<Void> updateExercise(@RequestParam("id") Long exerciseId, @RequestParam(name = "image") Optional<MultipartFile> image, @RequestPart(name = "exerciseData") String exerciseData) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.coercionConfigFor(LogicalType.Array).setCoercion(CoercionInputShape.EmptyArray, CoercionAction.AsEmpty);
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        ExerciseDto exerciseDto = objectMapper.readValue(exerciseData, ExerciseDto.class);
        try {
            exerciseFacade.updateExercise(exerciseId, image, exerciseDto, SecurityContextUtils.getLoggedUserDetails());
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
