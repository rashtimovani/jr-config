package net.rashack.externalvalues.implementation.values;

import static java.util.stream.Collectors.toList;

import java.net.URI;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.rashack.externalvalues.exceptions.InvalidValueProviderException;
import net.rashack.externalvalues.provider.ValueProvider;
import net.rashack.externalvalues.provider.ValueProviderFactory;

public class ValueProviders {

	public static ValueProviders produce() {
		final ValueProviders valueProviders = new ValueProviders();
		valueProviders.registerValueProvider("properties", PropertiesProvider::new);
		return valueProviders;
	}

	private final ConcurrentMap<String, ValueProviderFactory> providerFactories;

	private ValueProviders() {
		providerFactories = new ConcurrentHashMap<>();
	}

	public ValueProvider forURI(final URI uri) {
		final ValueProviderFactory factory = providerFactories.get(uri.getScheme());
		if (factory == null) {
			throw new InvalidValueProviderException(
					"There's no value provider of type: " + uri.getScheme() + "\n" + this);
		}
		return factory.forURI(uri);
	}

	public void registerValueProvider(final String providerType, final ValueProviderFactory providerFactory) {
		providerFactories.put(providerType, providerFactory);
	}

	@Override
	public String toString() {
		return "Available providers: " + providerFactories.values().stream().sorted().collect(toList());
	}
}
