package spring.boot.example.wicket.components;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;

import spring.boot.example.wicket.model.Ingredient;
import spring.boot.example.wicket.model.Ingredient.Unit;
import spring.boot.example.wicket.model.Recipe;

public class RecipeViewPage extends WebPage {

	public RecipeViewPage(Recipe recipe) {
		super(new CompoundPropertyModel<Recipe>(recipe));
        add(new Label("title"));
        add(new MultiLineLabel("description"));
		ListView<Ingredient> listView = new ListView<Ingredient>("ingredients", recipe.getIngredients()) {

			@Override
			protected void populateItem(ListItem<Ingredient> item) {
				Ingredient ingredient = item.getModel().getObject();
				item.add(new Label("amount", new PropertyModel<Float>(ingredient, "amount")))
					.add(new Label("unit", new PropertyModel<Unit>(ingredient, "unit")))
					.add(new Label("name", new PropertyModel<String>(ingredient, "name")));
			}
			
		};
		add(listView);
	}
}
