package net.rashack.externalvalues.implementation.convert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNull.nullValue;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class TestByteConverter {

	@Parameters({
			"42|42", "0x10|16", "-43|-43", "127|127", "-128|-128", "0|0", "0b1111|15"
	})
	@Test
	public void testConvertingValidValues(final String valueFromResource, final byte value) {
		assertThat(new ByteConverter().convert(valueFromResource), equalTo(value));
	}

	@Parameters({
			"asdad", "0c10", "00 0 0", "0x0R", "0b11441", "0x", "128", "-129", "0b", "24.0", "23.2", "256"
	})
	@Test(expected = NumberFormatException.class)
	public void testConvertingInvalidValues(final String valueFromResource) {
		new ByteConverter().convert(valueFromResource);
	}

	@Test
	public void testEmptyValueConvertsToNull() {
		assertThat(new ByteConverter().convert(""), nullValue());
	}
}
