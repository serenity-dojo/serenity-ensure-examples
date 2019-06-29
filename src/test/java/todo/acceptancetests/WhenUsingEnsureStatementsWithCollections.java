package todo.acceptancetests;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.ensure.web.NamedExpectation;

@RunWith(SerenityRunner.class)

public class WhenUsingEnsureStatementsWithCollections {
	
	Actor penny = Actor.named("Penny");

	List<String> colors = Arrays.asList("red","green","blue");
	List<String> secondaryColors = Arrays.asList("green","orange","brown");
	
	public static final NamedExpectation<String> IS_A_PRIMARY_COLOR = new NamedExpectation<>(
			"a primary color", 
			color -> color.equals("red") || color.equals("blue") || color.equals("yellow")
			);
	
	@Test
	public void matchingColors() {
		penny.attemptsTo(
				Ensure.that(colors).contains("red","blue"),
				Ensure.that(colors).hasSize(3),
				Ensure.that(colors).isNotEmpty(),
				Ensure.that(colors).doesNotHaveDuplicates(),
				Ensure.that(colors).containsAnyOf("red","pink"),
				Ensure.that(colors).doesNotContain("pink")
				);
	}
	
	@Test
	public void containsAPrimaryColor() {
		penny.attemptsTo( 
				Ensure.that(colors).anyMatch(IS_A_PRIMARY_COLOR)
				);
	}
	
}
