package net.rashack.externalvalues.implementation;

import static java.util.Collections.unmodifiableCollection;

import java.net.URI;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import net.rashack.externalvalues.ConfigureExternal;
import net.rashack.externalvalues.convert.ExternalConverter;
import net.rashack.externalvalues.exceptions.InvalidValueProviderException;
import net.rashack.externalvalues.exceptions.NoConverterException;
import net.rashack.externalvalues.implementation.configure.ConfigureConvertedExternal;
import net.rashack.externalvalues.implementation.convert.PredefinedConverters;
import net.rashack.externalvalues.implementation.values.ValueProviders;
import net.rashack.externalvalues.provider.ValueProvider;
import net.rashack.externalvalues.provider.ValueProviderFactory;

public final class ManipulateExternals {
	private class ValueProviderProxy implements ValueProvider {

		@Override
		public Optional<String> forKey(final String key) {
			final Collection<ValueProvider> providers = activeProviders.get();
			for (final ValueProvider provider : providers) {
				final Optional<String> value = provider.forKey(key);
				if (value.isPresent()) {
					return value;
				}
			}
			return Optional.empty();
		}
	}

	public static ManipulateExternals produce() {
		return new ManipulateExternals(PredefinedConverters.produce(), ValueProviders.produce());
	}

	private final AtomicReference<Collection<ValueProvider>> activeProviders = new AtomicReference<>(
			new LinkedList<>());
	private final PredefinedConverters predefinedConverters;
	private final ValueProviders valueProviders;

	ManipulateExternals(final PredefinedConverters predefinedConverters, final ValueProviders valueProviders) {
		this.predefinedConverters = predefinedConverters;
		this.valueProviders = valueProviders;
	}

	public <T> ConfigureExternal<T> converted(final ExternalConverter<T> converter) {
		if (converter == null) {
			throw new NoConverterException("Cannot provide null converter!");
		}
		return new ConfigureConvertedExternal<>(converter, new ValueProviderProxy());
	}

	public void readFromValues(final String... definitions) {
		final Deque<ValueProvider> providers = new LinkedList<>();
		for (final String definition : definitions) {
			final URI uri = tryToProduceURI(definition);
			final ValueProvider provider = valueProviders.forURI(uri);
			if (provider == null) {
				throw new InvalidValueProviderException(
						"Factory for " + uri.getScheme() + " returned null provider for URI: " + uri);
			}
			providers.addFirst(provider);
		}
		activeProviders.set(unmodifiableCollection(providers));
	}

	public void registerValueProvider(final String providerType, final ValueProviderFactory providerFactory) {
		valueProviders.registerValueProvider(providerType, providerFactory);
	}

	private URI tryToProduceURI(final String definition) {
		try {
			final URI providerURI = URI.create(definition);
			final String scheme = providerURI.getScheme();
			if (scheme == null) {
				throw new InvalidValueProviderException("Cannot load values from unspecified provider for URI: "
						+ definition + InvalidValueProviderException.SPECIFICATION);
			}
			return providerURI;
		} catch (final IllegalArgumentException e) {
			throw new InvalidValueProviderException("Malformed provider definition for URI: " + definition
					+ InvalidValueProviderException.SPECIFICATION, e);
		}
	}

	public <T> ConfigureExternal<T> typed(final Class<T> type) {
		final ExternalConverter<T> converter = predefinedConverters.forType(type);
		return new ConfigureConvertedExternal<>(converter, new ValueProviderProxy());
	}
}
