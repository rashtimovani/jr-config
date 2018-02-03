package net.rashack.externalvalues.implementation.convert;

public class LongConverter extends IntegerLikeConverter<Long> {

	@Override
	Long valueOf(final String value, final int radix) {
		return Long.valueOf(value, radix);
	}
}
