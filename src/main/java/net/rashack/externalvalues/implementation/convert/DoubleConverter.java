package net.rashack.externalvalues.implementation.convert;

import net.rashack.externalvalues.convert.ExternalConverter;

public class DoubleConverter implements ExternalConverter<Double> {

	@Override
	public Double convert(final String valueFromResource) {
		final String trimmed = valueFromResource.trim();
		if (trimmed.isEmpty()) {
			return defaultValue();
		}
		return Double.valueOf(trimmed);
	}

	Double defaultValue() {
		return null;
	}
}
