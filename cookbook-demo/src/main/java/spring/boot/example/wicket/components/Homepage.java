package spring.boot.example.wicket.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;

import spring.boot.example.wicket.model.Recipe;

/**
 * sample homepage
 *
 * @author Stefan Kloe
 *
 */
public class Homepage extends WebPage {

	private static final List<Recipe> recipes = Collections.synchronizedList(new ArrayList<Recipe>());
	
	public Homepage() {
        // Add comment form
        add(new RecipeForm("recipeForm"));
        // Add commentListView of existing comments
        add(new PropertyListView<Recipe>("recipes", recipes) {
            @Override
            public void populateItem(final ListItem<Recipe> listItem) {
                listItem.add(new MultiLineLabel("description"));
            }
        }).setVersioned(false);
    }

//    @Override
//    public void renderHead(IHeaderResponse response) {
//        super.renderHead(response);
//        CssResourceReference cssResourceReference = new CssResourceReference(
//                WicketWebApplication.class, "example.css");
//        response.render(CssHeaderItem.forReference(cssResourceReference));
//    }
}
