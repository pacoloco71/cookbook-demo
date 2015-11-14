package spring.boot.example.wicket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.boot.example.wicket.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
