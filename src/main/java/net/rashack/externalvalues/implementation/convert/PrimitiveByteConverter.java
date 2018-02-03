package net.rashack.externalvalues.implementation.convert;

public class PrimitiveByteConverter extends ByteConverter {

	@Override
	Byte defaultValue() {
		return Byte.valueOf((byte) 0);
	}
}
