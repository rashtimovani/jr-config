package net.rashack.externalvalues.implementation.convert;

import net.rashack.externalvalues.convert.ExternalConverter;

public class IntegerConverter implements ExternalConverter<Integer> {

	@Override
	public Integer convert(final String valueFromResource) {
		return valueFromResource != null ? Integer.valueOf(valueFromResource.trim()) : null;
	}
}
