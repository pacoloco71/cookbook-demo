package spring.boot.example.wicket.components;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

import spring.boot.example.wicket.model.Recipe;

public class RecipeEditPage extends WebPage {
	
	public RecipeEditPage() {
        add(new RecipeForm("recipeForm"))
        .add(new Label("editPageTitle", "Add a New Recipe"));
	}

	public RecipeEditPage(Recipe recipe) {
        add(new RecipeForm("recipeForm", recipe))
        .add(new Label("editPageTitle", "Edit your Recipe"));
	}

}
