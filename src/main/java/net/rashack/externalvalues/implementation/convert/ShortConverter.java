package net.rashack.externalvalues.implementation.convert;

public class ShortConverter extends IntegerLikeConverter<Short> {

	@Override
	Short valueOf(final String value, final int radix) {
		return Short.valueOf(value, radix);
	}
}
