package net.rashack.externalvalues.implementation.convert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNull.nullValue;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class TestShortConverter {

	@Parameters({
			"42|42", "0x10|16", "-4343|-4343", "32767|32767", "-32768|-32768", "0|0", "0b1111|15"
	})
	@Test
	public void testConvertingValidValues(final String valueFromResource, final short value) {
		assertThat(new ShortConverter().convert(valueFromResource), equalTo(value));
	}

	@Parameters({
			"asdad", "0c10", "00 0 0", "0x0R", "0b11441", "0x", "0b", "32768", "-32769", "24.0", "23.2",
			"344444444444444444444433"
	})
	@Test(expected = NumberFormatException.class)
	public void testConvertingInvalidValues(final String valueFromResource) {
		new ShortConverter().convert(valueFromResource);
	}

	@Test
	public void testEmptyValueConvertsToNull() {
		assertThat(new ShortConverter().convert(""), nullValue());
	}
}
