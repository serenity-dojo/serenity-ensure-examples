package todo.acceptancetests;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;
import org.junit.runner.RunWith;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;

@RunWith(SerenityRunner.class)
public class WhenUsingSimpleEnsureStatements {
	
	LocalDate januaryFirst2015 = LocalDate.of(2015, Month.JANUARY, 1);
	LocalDate januaryFirst2018 = LocalDate.of(2018, Month.JANUARY, 1);

	Actor penny = Actor.named("Penny");
	Pet fido = new Pet("Fido the dog", januaryFirst2015);

	@Test
    public void we_should_be_able_to_make_assertions_about_strings() {
    	
    	penny.attemptsTo(
    			Ensure.that(fido.getName()).isEqualTo("Fido the dog"),
    			Ensure.that(fido.getName()).startsWith("Fido"),
    			Ensure.that(fido.getName()).endsWith("the dog")
    	);
    }
	
	@Test
	public void we_should_be_able_to_make_assertions_about_integers() {
		
		penny.attemptsTo(
				Ensure.that(fido.getAge()).isGreaterThan(1)
		);
	}
	
	@Test
	public void we_can_compare_dates() {
		penny.attemptsTo(
			Ensure.that(fido.getDateOfBirth()).isBefore(januaryFirst2018)
		);
	}
}
