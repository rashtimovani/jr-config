package net.rashack.externalvalues.implementation.convert;

public class IntegerConverter extends IntegerLikeConverter<Integer> {

	@Override
	Integer valueOf(final String value, final int radix) {
		return Integer.valueOf(value, radix);
	}
}
