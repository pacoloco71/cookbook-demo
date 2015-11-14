package spring.boot.example.wicket.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.boot.example.wicket.model.Recipe;
import spring.boot.example.wicket.repositories.RecipeRepository;

@Service
@Transactional
public class RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;
	
	public Recipe findRecipeById(long id) {
		return recipeRepository.findOne(id);
	}
	
	public List<Recipe> findAllRecipes() {
		return recipeRepository.findAll();
	}
	
	public Recipe saveRecipe(Recipe recipe) {
		return recipeRepository.save(recipe);
	}
	
	public void removeRecipeById(long id) {
		recipeRepository.delete(id);
	}
}
