package net.rashack.externalvalues.implementation.convert;

import net.rashack.externalvalues.convert.ExternalConverter;

public class CharacterConverter implements ExternalConverter<Character> {

	@Override
	public Character convert(final String valueFromResource) {
		final String value = valueFromResource.trim();
		if (value.isEmpty()) {
			return defaultValue();
		}
		if (value.length() == 1) {
			return value.charAt(0);
		}
		throw new IllegalArgumentException("Value " + value + " has too many characters!");
	}

	Character defaultValue() {
		return null;
	}
}
