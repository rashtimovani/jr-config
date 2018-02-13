package net.rashack.externalvalues.implementation.convert;

import net.rashack.externalvalues.convert.ExternalConverter;

public class EnumConverter<T extends Enum<T>> implements ExternalConverter<T> {

	private final Class<T> enumClass;

	public EnumConverter(final Class<T> enumClass) {
		this.enumClass = enumClass;
	}

	@Override
	public T convert(final String valueFromResource) {
		final String value = valueFromResource.trim();
		if (value.isEmpty()) {
			return null;
		}
		return Enum.valueOf(enumClass, value);
	}

}
