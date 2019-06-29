package todo.acceptancetests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Managed;

@RunWith(SerenityRunner.class)
public class WhenUsingEnsureStatementsInWebTests {

	private static final Target NEW_TODO_FIELD = Target.the("new todo field")
			.located(By.cssSelector(".new-todo"));
	
	private static final Target LIST_ENTRY = Target.the("list entry").locatedBy(".todo-list label");

	private static final Target REMAINING_TODO_ITEMS = Target.the("remaining todo items")
			.locatedBy(".todo-count strong");
	
	@Managed
	WebDriver driver;
	
	Actor todd = Actor.named("Todd");
	
	@Before
	public void prepareDriver() {
		todd = todd.can(BrowseTheWeb.with(driver));
	}
	
	@Test
	public void weCanMakeAssertionsAboutPageTitles() {
		todd.attemptsTo(
				Open.url("http://todomvc.com"),
				Ensure.thatTheCurrentPage().title().isEqualTo("TodoMVC")
				);
	}
	
	@Test
	public void weCanCheckThatFieldsAreDisplayed() {
		todd.attemptsTo(
				Open.url("http://todomvc.com/examples/angularjs/#/"),
				Ensure.that(NEW_TODO_FIELD).isDisplayed()
				);
	}
	
	@Test
	public void weCanReadTheValueOfAField() {
		todd.attemptsTo(
				Open.url("http://todomvc.com/examples/angularjs/#/"),
				Enter.theValue("Walk the dog").into(NEW_TODO_FIELD),
				Ensure.that(NEW_TODO_FIELD).value().isEqualTo("Walk the dog")
				);
	}
	
	@Test
	public void weCanReadAttributes() {
		todd.attemptsTo(
				Open.url("http://todomvc.com/examples/angularjs/#/"),
				Ensure.that(NEW_TODO_FIELD).attribute("placeholder").isEqualTo("What needs to be done?")
				);
	}
	
	@Test
	public void weCanReadTextContent() {
		todd.attemptsTo(
				Open.url("http://todomvc.com/examples/angularjs/#/"),
				Enter.theValue("Walk the dog").into(NEW_TODO_FIELD).thenHit(Keys.ENTER),
				Ensure.that(LIST_ENTRY).text().isEqualTo("Walk the dog")
				);
		
	}
	
	@Test
	public void weCanConvertTextContentToOtherTypes() {
		todd.attemptsTo(
				Open.url("http://todomvc.com/examples/angularjs/#/"),
				Enter.theValue("Walk the dog").into(NEW_TODO_FIELD).thenHit(Keys.ENTER),
				Ensure.that(REMAINING_TODO_ITEMS).text().asAnInteger().isEqualTo(1)
				);
		
	}
	
	@Test
	public void weCanReadCollectionsOfTextValues() {
		todd.attemptsTo(
				Open.url("http://todomvc.com/examples/angularjs/#/"),
				Enter.theValue("Walk the dog").into(NEW_TODO_FIELD).thenHit(Keys.ENTER),
				Enter.theValue("Feed the cat").into(NEW_TODO_FIELD).thenHit(Keys.ENTER),
				Ensure.that(LIST_ENTRY).textValues().contains("Walk the dog","Feed the cat")
				);
		
	}
	
	
}
