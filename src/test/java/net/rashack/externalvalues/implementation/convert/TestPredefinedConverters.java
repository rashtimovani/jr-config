package net.rashack.externalvalues.implementation.convert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

import org.junit.Test;

import net.rashack.externalvalues.exceptions.NoConverterException;

public class TestPredefinedConverters {

	@Test
	public void testByteConverterPredefined() {
		assertThat(PredefinedConverters.produce().forType(Byte.class), instanceOf(ByteConverter.class));
	}

	@Test
	public void testIntegerConverterPredefined() {
		assertThat(PredefinedConverters.produce().forType(Integer.class), instanceOf(IntegerConverter.class));
	}

	@Test
	public void testLongConverterPredefined() {
		assertThat(PredefinedConverters.produce().forType(Long.class), instanceOf(LongConverter.class));
	}

	@Test
	public void testPrimitiveByteConverterPredefined() {
		assertThat(PredefinedConverters.produce().forType(byte.class), instanceOf(PrimitiveByteConverter.class));
	}

	@Test
	public void testPrimitiveIntConverterPredefined() {
		assertThat(PredefinedConverters.produce().forType(int.class), instanceOf(PrimitiveIntConverter.class));
	}

	@Test
	public void testPrimitiveLongConverterPredefined() {
		assertThat(PredefinedConverters.produce().forType(long.class), instanceOf(PrimitiveLongConverter.class));
	}

	@Test
	public void testPrimitiveShortConverterPredefined() {
		assertThat(PredefinedConverters.produce().forType(short.class), instanceOf(PrimitiveShortConverter.class));
	}

	@Test
	public void testShortConverterPredefined() {
		assertThat(PredefinedConverters.produce().forType(Short.class), instanceOf(ShortConverter.class));
	}

	@Test
	public void testStringConverterPredefined() {
		assertThat(PredefinedConverters.produce().forType(String.class), instanceOf(StringConverter.class));
	}

	@Test(expected = NoConverterException.class)
	public void testUnknownPredefinedType() {
		PredefinedConverters.produce().forType(TestPredefinedConverters.class);
	}
}
