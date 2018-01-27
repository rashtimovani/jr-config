package net.rashack.externalvalues.implementation.convert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

import org.junit.Test;

import net.rashack.externalvalues.exceptions.NoConverterException;

public class TestPredefinedConverters {

	@Test
	public void testIntegerConverterPredefined() {
		assertThat(PredefinedConverters.produce().forType(Integer.class), instanceOf(IntegerConverter.class));
	}

	@Test(expected = NoConverterException.class)
	public void testUnknownPredefinedType() {
		PredefinedConverters.produce().forType(TestPredefinedConverters.class);
	}
}
