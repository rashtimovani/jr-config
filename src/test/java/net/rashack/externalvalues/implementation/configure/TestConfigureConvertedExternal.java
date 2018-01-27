package net.rashack.externalvalues.implementation.configure;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import net.rashack.externalvalues.convert.ExternalConverter;
import net.rashack.externalvalues.exceptions.ExternalBuiltException;
import net.rashack.externalvalues.exceptions.InvalidKeyException;

@RunWith(JUnitParamsRunner.class)
public class TestConfigureConvertedExternal {

	static String[] invalidKeys() {
		return new String[] {
				"", "dsf\tsdf", "arer asd", "dsdf\nd", null
		};
	}

	private ExternalConverter<Integer> converter;

	@Before
	public void setUp() {
		converter = Integer::parseInt;
	}

	@Test(expected = ExternalBuiltException.class)
	public void testAlreadyUsedConfigurator() {
		final ConfigureConvertedExternal<Integer> configurator = spy(new ConfigureConvertedExternal<>(converter));
		configurator.buildForKey("testKey");

		configurator.buildForKey("testKey");
	}

	@Test
	public void testDefaultValueSet() {
		final ConfigureConvertedExternal<Integer> configurator = spy(new ConfigureConvertedExternal<>(converter));

		configurator.defaultValue(44).buildForKey("testKey");

		verify(configurator, Mockito.times(1)).instantiateExternal(eq(converter), eq("testKey"), eq(44));
	}

	@Parameters(method = "invalidKeys")
	@Test(expected = InvalidKeyException.class)
	public void testInvalidKey(final String key) {
		new ConfigureConvertedExternal<>(converter).buildForKey(key);
	}

	@Test(expected = InvalidKeyException.class)
	public void testLeadingWhiteSpace() {
		new ConfigureConvertedExternal<>(converter).buildForKey("   asdafdafa");
	}

	@Test(expected = InvalidKeyException.class)
	public void testTrailingWhiteSpace() {
		new ConfigureConvertedExternal<>(converter).buildForKey("asdafdafa ");
	}
}
