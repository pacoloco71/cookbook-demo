package spring.boot.example.wicket.components;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import spring.boot.example.wicket.model.Ingredient;
import spring.boot.example.wicket.model.Ingredient.Unit;
import spring.boot.example.wicket.model.Recipe;
import spring.boot.example.wicket.services.RecipeService;

public class RecipeForm extends Form<Recipe> {

	private static final int MAX_NO_OF_INGREDIENTS = 10;
	
	@SpringBean
	private RecipeService recipeService;

	public RecipeForm(final String id) {
		super(id, new CompoundPropertyModel<Recipe>(new Recipe()));
		addComponents();
	}

	public RecipeForm(final String id, Recipe recipe) {
		super(id, new CompoundPropertyModel<Recipe>(recipe));
		addComponents();
	}
	
	private void addComponents() {
		Recipe recipe = getModelObject();
		List<Ingredient> ingredients = recipe.getIngredients();
		while (ingredients.size() < MAX_NO_OF_INGREDIENTS) {
			Ingredient addIngredient = new Ingredient();
			addIngredient.setRecipe(recipe);
			ingredients.add(addIngredient);
		}
		// this is just to make the unit test happy
		setMarkupId("recipeForm");
		add(new TextField<String>("title"));
		// Add text entry widget
		add(new TextArea<String>("description"));
		ListView<Ingredient> listView = new ListView<Ingredient>("ingredients", ingredients) {

			@Override
			protected void populateItem(ListItem<Ingredient> item) {
				Ingredient ingredient = item.getModel().getObject();
				item.add(new TextField<String>("amount", new PropertyModel<String>(ingredient, "amount")))
					.add(new DropDownChoice("unit", new PropertyModel(ingredient, "unit"), new LoadableDetachableModel() {

						@Override
						protected List<Unit> load() {
							return Ingredient.getUnits();
						}
						
					}))
					.add(new TextField("name", new PropertyModel(ingredient, "name")));
			}
			
		};
		listView.setReuseItems(true);
		add(listView);
	}

	/**
	 * Show the resulting valid edit
	 */
	@Override
	public final void onSubmit() {
		Recipe recipe = getModelObject();
		List<Ingredient> ingredients = new ArrayList<>();
		for (Ingredient ingredient : recipe.getIngredients()) {
			if (ingredient.getAmount() != null && ingredient.getAmount() > 0 && ingredient.getUnit() != null
					&& ingredient.getName() != null && !ingredient.getName().equals("")) {
				//ingredient.setRecipe(recipe);
				ingredients.add(ingredient);
			}
		}
		recipe.setIngredients(ingredients);
		if (recipe.getTitle() != null) {
			recipeService.saveRecipe(recipe);
		}
		setResponsePage(new RecipeOverviewPage());
	}
}
