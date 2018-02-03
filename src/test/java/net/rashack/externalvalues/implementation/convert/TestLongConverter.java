package net.rashack.externalvalues.implementation.convert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNull.nullValue;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class TestLongConverter {

	@Parameters({
			"42|42", "0x10|16", "-4343|-4343", "9223372036854775807|9223372036854775807",
			"-9223372036854775808|-9223372036854775808", "0|0", "0b1111|15"
	})
	@Test
	public void testConvertingValidValues(final String valueFromResource, final long value) {
		assertThat(new LongConverter().convert(valueFromResource), equalTo(value));
	}

	@Parameters({
			"asdad", "0c10", "00 0 0", "0x0R", "0b11441", "0x", "0b", "18446744073709551616", "-18446744073709551617",
			"24.0", "23.2", "3444444444444444444444355555555555555555555555555555555555555555555553"
	})
	@Test(expected = NumberFormatException.class)
	public void testConvertingInvalidValues(final String valueFromResource) {
		new LongConverter().convert(valueFromResource);
	}

	@Test
	public void testEmptyValueConvertsToNull() {
		assertThat(new LongConverter().convert(""), nullValue());
	}
}
