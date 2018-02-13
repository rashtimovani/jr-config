package net.rashack.externalvalues.implementation.convert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class TestBooleanConverter {

	@Test
	public void testDefaultValue() {
		assertThat(new BooleanConverter().convert(""), equalTo(null));
	}

	@Parameters({
			"false|false", "FALSE|false", "faLSE|false", "true|true", "TRUE|true", "tRuE|true"
	})
	@Test
	public void testParsingBoolean(final String value, final boolean expected) {
		assertThat(new BooleanConverter().convert(value), equalTo(expected));
	}

	@Parameters({
			"yes", "no", "?", "ssss", "ttrue", "fallse", "3454", "1", "0", "4 4 4 4"
	})
	@Test(expected = IllegalArgumentException.class)
	public void testParsingInvalidBoolean(final String value) {
		new BooleanConverter().convert(value);
	}

	@Test
	public void testTrimmedValue() {
		assertThat(new BooleanConverter().convert("     false     "), equalTo(false));
	}
}
