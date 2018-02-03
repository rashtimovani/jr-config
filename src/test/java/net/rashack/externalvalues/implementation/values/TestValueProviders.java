package net.rashack.externalvalues.implementation.values;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

import java.net.URI;

import org.junit.Test;

import net.rashack.externalvalues.ValueProvider;
import net.rashack.externalvalues.exceptions.InvalidValueProviderException;

public class TestValueProviders {

	@Test
	public void testCustomValueProvider() {
		final ValueProviders providers = ValueProviders.produce();
		providers.registerValueProvider("inMemory", uri -> new InMemoryProvider());

		final ValueProvider custom = providers.forURI(URI.create("inMemory://test"));

		assertThat(custom, instanceOf(InMemoryProvider.class));
	}

	@Test
	public void testPropertiesValueProvider() {
		final ValueProvider properties = ValueProviders.produce()
				.forURI(URI.create("properties://src/test/resources/TestProperties.properties"));

		assertThat(properties, instanceOf(PropertiesProvider.class));
	}

	@Test(expected = InvalidValueProviderException.class)
	public void testUnknownValueProvider() {
		ValueProviders.produce().forURI(URI.create("unknownValiueProvider://who-knows-what"));
	}
}
