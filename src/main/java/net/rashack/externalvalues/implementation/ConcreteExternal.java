package net.rashack.externalvalues.implementation;

import net.rashack.externalvalues.External;
import net.rashack.externalvalues.convert.ExternalConverter;

public class ConcreteExternal<T> implements External<T> {
	private final ExternalConverter<T> converter;
	private final String key;
	private final T defaultValue;
	
	public ConcreteExternal(ExternalConverter<T> converter, String key, final T defaultValue) {
		this.converter = converter;
		this.key = key;
		this.defaultValue = defaultValue;
	}

	@Override
	public T value() {
		// TODO Auto-generated method stub
		return null;
	}
}
