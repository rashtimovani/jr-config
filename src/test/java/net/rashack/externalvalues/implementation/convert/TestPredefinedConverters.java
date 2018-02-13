package net.rashack.externalvalues.implementation.convert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

import org.junit.Test;

import net.rashack.externalvalues.exceptions.NoConverterException;

public class TestPredefinedConverters {

	@Test
	public void testBooleanConverterPredefined() {
		assertThat(PredefinedConverters.produce().forType(Boolean.class), instanceOf(BooleanConverter.class));
	}

	@Test
	public void testByteConverterPredefined() {
		assertThat(PredefinedConverters.produce().forType(Byte.class), instanceOf(ByteConverter.class));
	}

	@Test
	public void testCharacterConverterPredefined() {
		assertThat(PredefinedConverters.produce().forType(Character.class), instanceOf(CharacterConverter.class));
	}

	@Test
	public void testDoubleConverterPredefined() {
		assertThat(PredefinedConverters.produce().forType(Double.class), instanceOf(DoubleConverter.class));
	}

	@Test
	public void testFloatConverterPredefined() {
		assertThat(PredefinedConverters.produce().forType(Float.class), instanceOf(FloatConverter.class));
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
	public void testPrimitiveBooleanConverterPredefined() {
		assertThat(PredefinedConverters.produce().forType(boolean.class), instanceOf(PrimitiveBooleanConverter.class));
	}

	@Test
	public void testPrimitiveByteConverterPredefined() {
		assertThat(PredefinedConverters.produce().forType(byte.class), instanceOf(PrimitiveByteConverter.class));
	}

	@Test
	public void testPrimitiveCharConverterPredefined() {
		assertThat(PredefinedConverters.produce().forType(char.class), instanceOf(PrimitiveCharConverter.class));
	}

	@Test
	public void testPrimitiveDoubleConverterPredefined() {
		assertThat(PredefinedConverters.produce().forType(double.class), instanceOf(PrimitiveDoubleConverter.class));
	}

	@Test
	public void testPrimitiveFloatConverterPredefined() {
		assertThat(PredefinedConverters.produce().forType(float.class), instanceOf(PrimitiveFloatConverter.class));
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
