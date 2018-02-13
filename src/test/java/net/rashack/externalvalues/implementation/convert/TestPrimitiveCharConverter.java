package net.rashack.externalvalues.implementation.convert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class TestPrimitiveCharConverter {

	@Test
	public void testEmptyValueConvertsZeroChar() {
		assertThat(new PrimitiveCharConverter().convert(""), equalTo('\u0000'));
	}
}
