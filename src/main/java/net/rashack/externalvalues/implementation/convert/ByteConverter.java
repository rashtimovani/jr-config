package net.rashack.externalvalues.implementation.convert;

public class ByteConverter extends IntegerLikeConverter<Byte> {

	@Override
	Byte valueOf(final String value, final int radix) {
		return Byte.valueOf(value, radix);
	}
}
