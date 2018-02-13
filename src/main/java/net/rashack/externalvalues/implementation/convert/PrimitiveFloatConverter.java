package net.rashack.externalvalues.implementation.convert;

public class PrimitiveFloatConverter extends FloatConverter {

	@Override
	Float defaultValue() {
		return Float.valueOf(0f);
	}
}
