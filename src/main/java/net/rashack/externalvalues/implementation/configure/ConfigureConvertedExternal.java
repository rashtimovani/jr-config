package net.rashack.externalvalues.implementation.configure;

import java.util.regex.Pattern;

import net.rashack.externalvalues.ConfigureExternal;
import net.rashack.externalvalues.External;
import net.rashack.externalvalues.convert.ExternalConverter;
import net.rashack.externalvalues.exceptions.ExternalBuiltException;
import net.rashack.externalvalues.exceptions.InvalidKeyException;
import net.rashack.externalvalues.implementation.ConcreteExternal;

public class ConfigureConvertedExternal<T> implements ConfigureExternal<T> {
	private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s+");

	private final ExternalConverter<T> converter;

	private boolean instantiated;
	private T defaultValue;

	public ConfigureConvertedExternal(final ExternalConverter<T> converter) {
		this.converter = converter;
	}

	@Override
	public External<T> buildForKey(final String key) {
		if (instantiated) {
			throw new ExternalBuiltException();
		}
		if (key == null) {
			throw new InvalidKeyException("Key cannot be null!");
		}
		if (key.isEmpty()) {
			throw new InvalidKeyException("Key cannot be empty!");
		}
		if (WHITESPACE_PATTERN.matcher(key).find()) {
			throw new InvalidKeyException("Key \"" + key + "\" has white spaces in it!");
		}
		instantiated = true;
		return instantiateExternal(converter, key, defaultValue);
	}

	@Override
	public ConfigureExternal<T> defaultValue(final T value) {
		defaultValue = value;
		return this;
	}

	External<T> instantiateExternal(final ExternalConverter<T> converter, final String key, final T defaultValue) {
		return new ConcreteExternal<>(converter, key, defaultValue);
	}
}
