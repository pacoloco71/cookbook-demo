package spring.boot.example.wicket;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import spring.boot.example.wicket.components.RecipeOverviewPage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebInitializer.class,
		WicketWebApplication.class })
@WebAppConfiguration
@IntegrationTest
public class WicketWebApplicationTest {

	@Autowired
	private TestService testService;

	@Test
	public void testApplication() {
		WicketTester wicketTester = testService.getWicketTester();
		wicketTester.startPage(RecipeOverviewPage.class);
		wicketTester.assertRenderedPage(RecipeOverviewPage.class);
	}


}
