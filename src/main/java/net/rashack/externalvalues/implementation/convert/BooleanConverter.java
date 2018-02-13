package net.rashack.externalvalues.implementation.convert;

import net.rashack.externalvalues.convert.ExternalConverter;

public class BooleanConverter implements ExternalConverter<Boolean> {

	@Override
	public Boolean convert(final String valueFromResource) {
		final String value = valueFromResource.trim();
		if (value.isEmpty()) {
			return defaultValue();
		}
		if (Boolean.TRUE.toString().equalsIgnoreCase(value)) {
			return Boolean.TRUE;
		}
		if (Boolean.FALSE.toString().equalsIgnoreCase(value)) {
			return Boolean.FALSE;
		}
		throw new IllegalArgumentException("Provided value " + value + " cannot be parsed to boolean!");
	}

	Boolean defaultValue() {
		return null;
	}
}
