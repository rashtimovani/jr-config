package net.rashack.externalvalues.implementation.convert;

public class IntegerConverter extends IntConverter {

	@Override
	public Integer convert(final String valueFromResource) {
		return valueFromResource != null ? super.convert(valueFromResource) : null;
	}
}
