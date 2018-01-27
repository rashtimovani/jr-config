package net.rashack.externalvalues.implementation.convert;

import net.rashack.externalvalues.convert.ExternalConverter;

public class IntConverter implements ExternalConverter<Integer> {

	@Override
	public Integer convert(final String valueFromResource) {
		return Integer.valueOf(valueFromResource.trim());
	}
}
