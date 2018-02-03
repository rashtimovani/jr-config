package net.rashack.externalvalues.implementation.values;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.BeforeClass;
import org.junit.Test;

import net.rashack.externalvalues.exceptions.InvalidValueProviderException;

public class TestPropertiesProvider {
	private static PropertiesProvider properties;

	@BeforeClass
	public static void setUp() throws URISyntaxException {
		final ClassLoader classLoader = TestPropertiesProvider.class.getClassLoader();
		final URI propertiesFileURI = classLoader.getResource("TestProperties.properties").toURI();
		properties = new PropertiesProvider(propertiesFileURI);
	}

	@Test
	public void testAbsentKey() {
		assertThat(properties.forKey("absentProperty").isPresent(), equalTo(false));
	}

	@Test(expected = InvalidValueProviderException.class)
	public void testInvalidPropertiesFile() {
		new PropertiesProvider(URI.create("properties://unknonwFile"));
	}

	@Test
	public void testKnownEmptyKey() {
		assertThat(properties.forKey("emptyProperty").get(), equalTo(""));
	}

	@Test
	public void testKnownKey() {
		assertThat(properties.forKey("longProperty").get(), equalTo("234234"));
	}
}
