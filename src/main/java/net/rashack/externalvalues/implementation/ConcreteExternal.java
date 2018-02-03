package net.rashack.externalvalues.implementation;

import java.util.Optional;

import net.rashack.externalvalues.External;
import net.rashack.externalvalues.ValueProvider;
import net.rashack.externalvalues.convert.ExternalConverter;

public class ConcreteExternal<T> implements External<T> {
	private final ExternalConverter<T> converter;
	private final ValueProvider valueProvider;
	private final String key;
	private final T defaultValue;

	public ConcreteExternal(final ExternalConverter<T> converter, final ValueProvider valueProvider, final String key,
			final T defaultValue) {
		this.converter = converter;
		this.valueProvider = valueProvider;
		this.key = key;
		this.defaultValue = defaultValue;
	}

	@Override
	public T value() {
		final Optional<String> valueFromResource = valueProvider.forKey(key);
		if (valueFromResource.isPresent()) {
			return converter.convert(valueFromResource.get());
		}
		return defaultValue;
	}
}
