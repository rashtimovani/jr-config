package net.rashack.externalvalues.implementation.convert;

import net.rashack.externalvalues.convert.ExternalConverter;

public class IntegerConverter implements ExternalConverter<Integer> {

	@Override
	public Integer convert(final String valueFromResource) {
		final String trimmed = valueFromResource.trim();
		if (trimmed.isEmpty()) {
			return null;
		}
		if (trimmed.startsWith("0x")) {
			return Integer.valueOf(trimmed.substring(2), 16);
		}
		if (trimmed.startsWith("0b")) {
			return Integer.valueOf(trimmed.substring(2), 2);
		}
		return Integer.valueOf(trimmed);
	}
}
