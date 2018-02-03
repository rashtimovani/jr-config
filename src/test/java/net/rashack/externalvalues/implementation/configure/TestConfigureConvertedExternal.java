package net.rashack.externalvalues.implementation.configure;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import net.rashack.externalvalues.convert.ExternalConverter;
import net.rashack.externalvalues.exceptions.ExternalBuiltException;
import net.rashack.externalvalues.exceptions.InvalidKeyException;
import net.rashack.externalvalues.provider.ValueProvider;

@RunWith(JUnitParamsRunner.class)
public class TestConfigureConvertedExternal {

	static String[] invalidKeys() {
		return new String[] {
				"", "dsf\tsdf", "arer asd", "dsdf\nd", null
		};
	}

	private ExternalConverter<Integer> converter;
	private ValueProvider valueProvider;

	@Before
	public void setUp() {
		converter = Integer::parseInt;
		valueProvider = key -> null;
	}

	@Test(expected = ExternalBuiltException.class)
	public void testAlreadyUsedConfigurator() {
		final ConfigureConvertedExternal<Integer> configurator = spy(
				new ConfigureConvertedExternal<>(converter, valueProvider));
		configurator.buildForKey("testKey");

		configurator.buildForKey("testKey");
	}

	@Test
	public void testDefaultValueSet() {
		final ConfigureConvertedExternal<Integer> configurator = spy(
				new ConfigureConvertedExternal<>(converter, valueProvider));

		configurator.defaultValue(44).buildForKey("testKey");

		verify(configurator, times(1)).instantiateExternal(eq(converter), eq("testKey"), eq(44));
	}

	@Parameters(method = "invalidKeys")
	@Test(expected = InvalidKeyException.class)
	public void testInvalidKey(final String key) {
		new ConfigureConvertedExternal<>(converter, valueProvider).buildForKey(key);
	}

	@Test(expected = InvalidKeyException.class)
	public void testLeadingWhiteSpace() {
		new ConfigureConvertedExternal<>(converter, valueProvider).buildForKey("   asdafdafa");
	}

	@Test(expected = InvalidKeyException.class)
	public void testTrailingWhiteSpace() {
		new ConfigureConvertedExternal<>(converter, valueProvider).buildForKey("asdafdafa ");
	}
}
