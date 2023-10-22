package pl.fitnote.exercise;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

interface ExerciseCategoryGroupQueryRepository extends JpaRepository<ExerciseCategoryGroup, Long> {

    Set<ExerciseCategoryGroup> findAllByCategoryNameIsIn(final List<ExerciseCategoryGroupEnum> exerciseCategoryGroupEnums);
}
