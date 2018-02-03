package net.rashack.externalvalues.implementation.values;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import net.rashack.externalvalues.exceptions.InvalidValueProviderException;

public class TestPropertiesProvider {
	private static PropertiesProvider properties;

	@BeforeClass
	public static void setUp() {
		final ClassLoader classLoader = TestPropertiesProvider.class.getClassLoader();
		final String propertiesFile = classLoader.getResource("TestProperties.properties").getFile();
		properties = new PropertiesProvider(new File(propertiesFile));
	}

	@Test
	public void testAbsentKey() {
		assertThat(properties.forKey("absentProperty").isPresent(), equalTo(false));
	}

	@Test(expected = InvalidValueProviderException.class)
	public void testInvalidPropertiesFile() {
		new PropertiesProvider(new File("unknonwFile"));
	}

	@Test(expected = InvalidValueProviderException.class)
	public void testDirectoryPropertiesFile() {
		new PropertiesProvider(new File("src"));
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
