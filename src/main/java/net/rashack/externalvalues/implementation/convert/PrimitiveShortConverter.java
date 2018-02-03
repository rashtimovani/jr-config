package net.rashack.externalvalues.implementation.convert;

public class PrimitiveShortConverter extends ShortConverter {

	@Override
	Short defaultValue() {
		return Short.valueOf((short) 0);
	}
}
