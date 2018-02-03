package net.rashack.externalvalues.implementation.convert;

public class PrimitiveLongConverter extends LongConverter {

	@Override
	Long defaultValue() {
		return Long.valueOf(0L);
	}
}
