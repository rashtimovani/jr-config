package net.rashack.externalvalues.implementation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import net.rashack.externalvalues.External;
import net.rashack.externalvalues.exceptions.InvalidValueProviderException;
import net.rashack.externalvalues.exceptions.NoConverterException;
import net.rashack.externalvalues.implementation.configure.ConfigureConvertedExternal;
import net.rashack.externalvalues.implementation.convert.PredefinedConverters;
import net.rashack.externalvalues.implementation.values.ValueProviders;

@RunWith(JUnitParamsRunner.class)
public class TestsManipulateExternals {

	@Test
	public void testFoundConverter() {
		assertThat(
				new ManipulateExternals(PredefinedConverters.produce(), ValueProviders.produce()).typed(Integer.class),
				instanceOf(ConfigureConvertedExternal.class));
	}

	@Test
	public void testNoOpValueProvider() {
		final External<Integer> external = new ManipulateExternals(PredefinedConverters.produce(),
				ValueProviders.produce()).typed(Integer.class).defaultValue(42).buildForKey("noOpValue");

		assertThat(external.value(), equalTo(42));
	}

	@Test(expected = NoConverterException.class)
	public void testNullConverter() {
		new ManipulateExternals(PredefinedConverters.produce(), ValueProviders.produce()).converted(null);
	}

	@Test
	public void testReadFromDefinitions() {
		new ManipulateExternals(PredefinedConverters.produce(), ValueProviders.produce())
				.readFromValues("properties://src/test/resources/TestProperties.properties");
	}

	@Parameters({
			"sadfsd", "asdasd:\\", "asd   ", "    ", "://sadasd"
	})
	@Test(expected = InvalidValueProviderException.class)
	public void testReadFromDefinitionsInvalidURI(final String invalidURI) {
		new ManipulateExternals(PredefinedConverters.produce(), ValueProviders.produce()).readFromValues(invalidURI);
	}

	@Test(expected = InvalidValueProviderException.class)
	public void testReadFromNullProvider() {
		final ManipulateExternals manipulateExternals = new ManipulateExternals(PredefinedConverters.produce(),
				ValueProviders.produce());
		manipulateExternals.registerValueProvider("null", uri -> null);

		manipulateExternals.readFromValues("null://test");
	}

	@Test(expected = NoConverterException.class)
	public void testUnknownConverter() {
		new ManipulateExternals(PredefinedConverters.produce(), ValueProviders.produce())
				.typed(TestsManipulateExternals.class);
	}
}
