package net.rashack.externalvalues.implementation.convert;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class TestIntLikeConverter {

	@Test
	public void testConvertingBinarFormat() {
		final IntegerLikeConverter<?> converter = spy(IntegerLikeConverter.class);

		converter.convert("0b00101010");

		verify(converter, times(0)).defaultValue();
		verify(converter, times(1)).valueOf(eq("1111"), eq(2));
	}

	@Test
	public void testConvertingDecimalFormat() {
		final IntegerLikeConverter<?> converter = spy(IntegerLikeConverter.class);

		converter.convert("42");

		verify(converter, times(0)).defaultValue();
		verify(converter, times(1)).valueOf(eq("42"), eq(10));
	}

	@Test
	public void testConvertingHexadecimalFormat() {
		final IntegerLikeConverter<?> converter = spy(IntegerLikeConverter.class);

		converter.convert("0x2A");

		verify(converter, times(0)).defaultValue();
		verify(converter, times(1)).valueOf(eq("2A"), eq(16));
	}

	@Test
	public void testEmptyValueAfterTrimReturnsDefault() {
		final IntegerLikeConverter<?> converter = spy(IntegerLikeConverter.class);

		converter.convert("\t  \t\n");

		verify(converter, times(1)).defaultValue();
		verify(converter, times(0)).valueOf(anyString(), anyInt());
	}

	@Test
	public void testEmptyValueReturnsDefault() {
		final IntegerLikeConverter<?> converter = spy(IntegerLikeConverter.class);

		converter.convert("");

		verify(converter, times(1)).defaultValue();
		verify(converter, times(0)).valueOf(anyString(), anyInt());
	}

	@Test
	public void testValueLeadingSpaces() {
		final IntegerLikeConverter<?> converter = spy(IntegerLikeConverter.class);

		converter.convert("\t 2A");

		verify(converter, times(0)).defaultValue();
		verify(converter, times(1)).valueOf(eq("2A"), anyInt());
	}

	@Test
	public void testValueTrailingSpaces() {
		final IntegerLikeConverter<?> converter = spy(IntegerLikeConverter.class);

		converter.convert("2A\t\n");

		verify(converter, times(0)).defaultValue();
		verify(converter, times(1)).valueOf(eq("2A"), anyInt());
	}
}
