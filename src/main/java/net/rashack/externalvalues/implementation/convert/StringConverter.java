package net.rashack.externalvalues.implementation.convert;

import net.rashack.externalvalues.convert.ExternalConverter;

public class StringConverter implements ExternalConverter<String> {

	@Override
	public String convert(final String valueFromResource) {
		return valueFromResource;
	}
}
