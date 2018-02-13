package net.rashack.externalvalues.implementation.convert;

import net.rashack.externalvalues.convert.ExternalConverter;

public class FloatConverter implements ExternalConverter<Float> {

	@Override
	public Float convert(final String valueFromResource) {
		final String trimmed = valueFromResource.trim();
		if (trimmed.isEmpty()) {
			return defaultValue();
		}
		return Float.valueOf(trimmed);
	}

	Float defaultValue() {
		return null;
	}
}
