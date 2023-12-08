package pl.fitnote.diet;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.fitnote.activity.Activity;

interface MealPersistRepository extends JpaRepository<Meal, Long> {
}
