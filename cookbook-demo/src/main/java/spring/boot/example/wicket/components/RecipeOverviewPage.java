package spring.boot.example.wicket.components;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

import spring.boot.example.wicket.model.Recipe;
import spring.boot.example.wicket.services.RecipeService;

public class RecipeOverviewPage extends WebPage {

	@SpringBean
	private RecipeService recipeService;

	public RecipeOverviewPage() {
		List<Recipe> recipes = recipeService.findAllRecipes();
		final Link actionOnClickLink = new Link("addRecipeLink") {
			@Override
			public void onClick() {
				setResponsePage(new RecipeEditPage());
			}
		};

		add(actionOnClickLink);
		add(new PropertyListView<Recipe>("recipes", recipes) {
			@Override
			public void populateItem(final ListItem<Recipe> listItem) {
				Recipe recipe = (Recipe) listItem.getDefaultModelObject();
				Link titleLink = new Link("viewRecipeLink") {

					@Override
					public void onClick() {
						setResponsePage(new RecipeViewPage(recipe));
					}
					
				};
				titleLink.add(new Label("title"));
				listItem.add(titleLink)
					.add(new Link("editRecipeLink") {

						@Override
						public void onClick() {
							setResponsePage(new RecipeEditPage(recipe));
						}
						
					})
					.add(new Link("removeRecipeLink") {

						@Override
						public void onClick() {
							recipeService.removeRecipeById(recipe.getId());
							setResponsePage(new RecipeOverviewPage());
						}
						
					});
			}
		}).setVersioned(false);
	}
}
