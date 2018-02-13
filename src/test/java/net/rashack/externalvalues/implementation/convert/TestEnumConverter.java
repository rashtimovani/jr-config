package net.rashack.externalvalues.implementation.convert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNull.nullValue;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class TestEnumConverter {

	@Parameters({
			"another", "value", "00 0 0", "0x0R", "0b11441", "0x", "0b", "aaaa", "Another", "anoTHER"
	})
	@Test(expected = IllegalArgumentException.class)
	public void testConvertingInvalidValues(final String valueFromResource) {
		new EnumConverter<>(SomeTestEnum.class).convert(valueFromResource);
	}

	@Parameters({
			"VALUE|VALUE", "ANOTHER|ANOTHER"
	})
	@Test
	public void testConvertingValidValues(final String valueFromResource, final SomeTestEnum value) {
		assertThat(new EnumConverter<>(SomeTestEnum.class).convert(valueFromResource), equalTo(value));
	}

	@Test
	public void testEmptyValueConvertsToNull() {
		assertThat(new EnumConverter<>(SomeTestEnum.class).convert(""), nullValue());
	}

	@Test
	public void testTrimmedValue() {
		assertThat(new EnumConverter<>(SomeTestEnum.class).convert("   VALUE "), equalTo(SomeTestEnum.VALUE));
	}
}
