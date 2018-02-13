package net.rashack.externalvalues.implementation.convert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class TestPrimitiveDoubleConverter {

	@Test
	public void testEmptyValueConvertsToZero() {
		assertThat(new PrimitiveDoubleConverter().convert(""), equalTo(0.0));
	}
}
