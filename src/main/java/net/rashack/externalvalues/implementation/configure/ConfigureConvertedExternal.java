package net.rashack.externalvalues.implementation.configure;

import java.util.regex.Pattern;

import net.rashack.externalvalues.ConfigureExternal;
import net.rashack.externalvalues.External;
import net.rashack.externalvalues.convert.ExternalConverter;
import net.rashack.externalvalues.exceptions.ExternalBuiltException;
import net.rashack.externalvalues.exceptions.InvalidKeyException;
import net.rashack.externalvalues.exceptions.KeyNotSetException;
import net.rashack.externalvalues.implementation.ExternalImpl;

public class ConfigureConvertedExternal<T> implements ConfigureExternal<T> {
	private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s");

	private final ExternalConverter<T> converter;

	private boolean instantiated;
	private String key;
	private T defaultValue;

	public ConfigureConvertedExternal(final ExternalConverter<T> converter) {
		this.converter = converter;
	}

	@Override
	public ConfigureExternal<T> defaultValue(final T value) {
		defaultValue = value;
		return this;
	}

	@Override
	public External<T> instantiate() {
		if (instantiated) {
			throw new ExternalBuiltException();
		}
		if (key == null) {
			throw new KeyNotSetException();
		}
		instantiated = true;
		return new ExternalImpl<>(converter, key, defaultValue);
	}

	@Override
	public ConfigureExternal<T> key(final String key) {
		if (key == null) {
			throw new InvalidKeyException("Key cannot be null!");
		}
		if (WHITESPACE_PATTERN.matcher(key).matches()) {
			throw new InvalidKeyException("Key \"" + key + "\" has white spaces in it!");
		}
		this.key = key;
		return this;
	}
}
