package net.rashack.externalvalues.implementation.convert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNull.nullValue;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class TestDoubleConverter {

	@Parameters({
			"asdad", "0c10", "00 0 0", "0x0R", "0b11441", "0x", "0b"
	})
	@Test(expected = NumberFormatException.class)
	public void testConvertingInvalidValues(final String valueFromResource) {
		new DoubleConverter().convert(valueFromResource);
	}

	@Parameters({
			"42.0|42.0", ".222|.222", "1.32e2|1.32e2", "-1.32e2|-1.32e2", "0|0", "-4|-4", "-0.2|-0.2", "-.3|-.3"
	})
	@Test
	public void testConvertingValidValues(final String valueFromResource, final double value) {
		assertThat(new DoubleConverter().convert(valueFromResource), equalTo(value));
	}

	@Test
	public void testEmptyValueConvertsToNull() {
		assertThat(new DoubleConverter().convert(""), nullValue());
	}

	@Test
	public void testTrimmedValue() {
		assertThat(new DoubleConverter().convert("   42.0 "), equalTo(42.0));
	}
}
