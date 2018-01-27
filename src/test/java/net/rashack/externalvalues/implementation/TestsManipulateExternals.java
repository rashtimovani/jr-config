package net.rashack.externalvalues.implementation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

import org.junit.Test;

import net.rashack.externalvalues.exceptions.NoConverterException;
import net.rashack.externalvalues.implementation.configure.ConfigureConvertedExternal;
import net.rashack.externalvalues.implementation.convert.PredefinedConverters;

public class TestsManipulateExternals {

	@Test(expected = NoConverterException.class)
	public void testNullConverter() {
		new ManipulateExternals(PredefinedConverters.produce()).converted(null);
	}

	@Test(expected = NoConverterException.class)
	public void testUnknownConverter() {
		new ManipulateExternals(PredefinedConverters.produce()).typed(TestsManipulateExternals.class);
	}

	@Test
	public void testFoundConverter() {
		assertThat(new ManipulateExternals(PredefinedConverters.produce()).typed(Integer.class),
				instanceOf(ConfigureConvertedExternal.class));
	}
}
