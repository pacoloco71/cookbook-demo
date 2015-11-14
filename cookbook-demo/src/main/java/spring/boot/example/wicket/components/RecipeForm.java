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

	@SpringBean
	private RecipeService recipeService;

	public RecipeForm(final String id) {
		// Construct form with no validation listener
		super(id, new CompoundPropertyModel<Recipe>(new Recipe()));
		addComponents();
	}

	public RecipeForm(final String id, Recipe recipe) {
		super(id, new CompoundPropertyModel<Recipe>(recipe));
		addComponents();
	}
	
	private void addComponents() {
		// this is just to make the unit test happy
		setMarkupId("recipeForm");
		add(new TextField<String>("title"));
		// Add text entry widget
		add(new TextArea<String>("description"));
		List<Ingredient> ingredients = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			ingredients.add(new Ingredient(1));
		}
		ListView<Ingredient> listView = new ListView<Ingredient>("ingredients", ingredients) {

			@Override
			protected void populateItem(ListItem<Ingredient> item) {
				item.add(new TextField<Ingredient>("amount", new PropertyModel<Ingredient>(item.getModel(), "amount")))
					.add(new DropDownChoice<Ingredient>("unit", new PropertyModel(item.getModel(), "unit"), new LoadableDetachableModel() {

						@Override
						protected List<Unit> load() {
							return Ingredient.getUnits();
						}
						
					}))
					.add(new TextField<Ingredient>("name", new PropertyModel<Ingredient>(item.getModel(), "name")));
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
		recipeService.saveRecipe(recipe);
		setResponsePage(new RecipeOverviewPage());
	}
}
