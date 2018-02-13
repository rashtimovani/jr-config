package net.rashack.externalvalues.implementation.convert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class TestCharacterConverter {

	@Test
	public void testDefaultValue() {
		assertThat(new CharacterConverter().convert(""), equalTo(null));
	}

	@Parameters({
			"a|a", "B|B", "9|9", "團|團", "?|?", "!|!"
	})
	@Test
	public void testParsingCharacter(final String value, final char expected) {
		assertThat(new CharacterConverter().convert(value), equalTo(expected));
	}

	@Parameters({
			"aa", "no", "ssss", "ttrue", "fallse", "3454", "4 4 4 4"
	})
	@Test(expected = IllegalArgumentException.class)
	public void testParsingInvalidCharacter(final String value) {
		new CharacterConverter().convert(value);
	}

	@Test
	public void testTrimmedValue() {
		assertThat(new CharacterConverter().convert("     5     "), equalTo('5'));
	}
}
