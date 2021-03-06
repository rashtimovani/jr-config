package net.rashack.externalvalues.implementation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import net.rashack.externalvalues.convert.ExternalConverter;
import net.rashack.externalvalues.provider.ValueProvider;

public class TestConcreteExternal {

	private ExternalConverter<Integer> converter;
	private ValueProvider valueProvider;

	@Before
	public void setUp() {
		converter = Integer::valueOf;
		valueProvider = mock(ValueProvider.class);
	}

	@Test
	public void testDefaultValue() {
		doReturn(Optional.empty()).when(valueProvider).forKey(eq("notInResources"));

		assertThat(new ConcreteExternal<>(converter, valueProvider, "notInResources", 4).value(), equalTo(4));
	}

	@Test
	public void testInResources() {
		doReturn(Optional.of("45")).when(valueProvider).forKey(eq("inResources"));

		assertThat(new ConcreteExternal<>(converter, valueProvider, "inResources", null).value(), equalTo(45));
	}

	@Test
	public void testInResourcesWithDefaultValue() {
		doReturn(Optional.of("45")).when(valueProvider).forKey(eq("inResourcesWithDefault"));

		assertThat(new ConcreteExternal<>(converter, valueProvider, "inResourcesWithDefault", 4).value(), equalTo(45));
	}

	@Test
	public void testNoValue() {
		doReturn(Optional.empty()).when(valueProvider).forKey(eq("noValue"));

		assertThat(new ConcreteExternal<>(converter, valueProvider, "noValue", null).value(), nullValue());
	}

	@Test
	public void testNullValueFromConvereter() {
		converter = value -> null;
		doReturn(Optional.of("")).when(valueProvider).forKey(eq("nullValue"));

		assertThat(new ConcreteExternal<>(converter, valueProvider, "nullValue", 42).value(), nullValue());
	}
}
