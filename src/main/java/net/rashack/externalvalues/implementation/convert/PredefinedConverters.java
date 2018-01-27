package net.rashack.externalvalues.implementation.convert;

import static java.util.Collections.unmodifiableMap;

import java.util.HashMap;
import java.util.Map;

import net.rashack.externalvalues.convert.ExternalConverter;
import net.rashack.externalvalues.exceptions.NoConverterException;

public class PredefinedConverters {
	private static Map<Class<?>, ExternalConverter<?>> defaults() {
		final Map<Class<?>, ExternalConverter<?>> defaults = new HashMap<>();
		defaults.put(Integer.class, new IntegerConverter());
		defaults.put(int.class, new IntConverter());
		return defaults;
	}

	public static PredefinedConverters produce() {
		return new PredefinedConverters(defaults());
	}

	private final Map<Class<?>, ExternalConverter<?>> converters;

	private PredefinedConverters(final Map<Class<?>, ExternalConverter<?>> converters) {
		this.converters = unmodifiableMap(converters);
	}

	@SuppressWarnings("unchecked")
	public <T> ExternalConverter<T> forType(final Class<T> type) {
		final ExternalConverter<?> externalConverter = converters.get(type);
		if (externalConverter == null) {
			throw new NoConverterException(
					"There's no converter for type: " + type + ", please provide converter instead of type!");
		}
		return (ExternalConverter<T>) externalConverter;
	}
}
