package net.rashack.externalvalues.implementation.convert;

import net.rashack.externalvalues.convert.ExternalConverter;

public abstract class IntegerLikeConverter<N extends Number> implements ExternalConverter<N> {

	@Override
	public N convert(final String valueFromResource) {
		final String trimmed = valueFromResource.trim();
		if (trimmed.isEmpty()) {
			return defaultValue();
		}
		if (trimmed.startsWith("0x")) {
			return valueOf(trimmed.substring(2), 16);
		}
		if (trimmed.startsWith("0b")) {
			return valueOf(trimmed.substring(2), 2);
		}
		return valueOf(trimmed, 10);
	}

	N defaultValue() {
		return null;
	}

	abstract N valueOf(final String value, final int radix);
}
