package net.rashack.externalvalues.implementation.convert;

import static java.util.Collections.unmodifiableMap;

import java.util.HashMap;
import java.util.Map;

import net.rashack.externalvalues.convert.ExternalConverter;
import net.rashack.externalvalues.exceptions.NoConverterException;

public class PredefinedConverters {
	private static Map<Class<?>, ExternalConverter<?>> defaults() {
		final Map<Class<?>, ExternalConverter<?>> defaults = new HashMap<>();

		defaults.put(Boolean.class, new BooleanConverter());
		defaults.put(boolean.class, new PrimitiveBooleanConverter());

		defaults.put(Byte.class, new ByteConverter());
		defaults.put(byte.class, new PrimitiveByteConverter());
		defaults.put(Short.class, new ShortConverter());
		defaults.put(short.class, new PrimitiveShortConverter());
		defaults.put(Integer.class, new IntegerConverter());
		defaults.put(int.class, new PrimitiveIntConverter());
		defaults.put(Long.class, new LongConverter());
		defaults.put(long.class, new PrimitiveLongConverter());

		defaults.put(Float.class, new FloatConverter());
		defaults.put(float.class, new PrimitiveFloatConverter());

		defaults.put(Character.class, new CharacterConverter());
		defaults.put(char.class, new PrimitiveCharConverter());

		defaults.put(String.class, new StringConverter());

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
