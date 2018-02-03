package net.rashack.externalvalues.implementation.convert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNull.nullValue;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class TestIntegerConverter {

	@Parameters({
			"42|42", "0x10|16", "-4343|-4343", "2000222000|2000222000", "0|0", "0b1111|15"
	})
	@Test
	public void testConvertingIntegers(final String valueFromResource, final int value) {
		assertThat(new IntegerConverter().convert(valueFromResource), equalTo(value));
	}

	@Parameters({
			"asdad", "0c10", "00 0 0", "0x0R", "0b11441", "0x", "0b", "24.0", "23.2", "344444444444444444444433"
	})
	@Test(expected = NumberFormatException.class)
	public void testConvertingInvalidValues(final String valueFromResource) {
		new IntegerConverter().convert(valueFromResource);
	}

	@Test
	public void testEmptyValueConvertsToNull() {
		assertThat(new IntegerConverter().convert(""), nullValue());
	}
}
