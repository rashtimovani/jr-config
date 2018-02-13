package net.rashack.externalvalues.implementation.convert;

public class PrimitiveDoubleConverter extends DoubleConverter {

	@Override
	Double defaultValue() {
		return Double.valueOf(0);
	}
}
