package net.rashack.externalvalues.implementation.convert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class TestPrimitiveIntConverter {

	@Test
	public void testEmptyValueConvertsToZero() {
		assertThat(new PrimitiveIntConverter().convert(""), equalTo(0));
	}
}
