package net.rashack.externalvalues.implementation.convert;

public class IntConverter extends IntegerConverter {

	@Override
	Integer defaultValue() {
		return Integer.valueOf(0);
	}
}
