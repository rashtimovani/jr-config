package net.rashack.externalvalues.implementation.convert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class TestPrimitiveFloatConverter {

	@Test
	public void testEmptyValueConvertsToZero() {
		assertThat(new PrimitiveFloatConverter().convert(""), equalTo(0f));
	}
}
