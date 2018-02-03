package net.rashack.externalvalues.implementation.convert;

public class PrimitiveIntConverter extends IntegerConverter {

	@Override
	Integer defaultValue() {
		return Integer.valueOf(0);
	}
}
