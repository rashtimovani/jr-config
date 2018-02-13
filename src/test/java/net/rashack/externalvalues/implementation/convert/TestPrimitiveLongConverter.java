package net.rashack.externalvalues.implementation.convert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class TestPrimitiveLongConverter {

	@Test
	public void testEmptyValueConvertsToZero() {
		assertThat(new PrimitiveLongConverter().convert(""), equalTo(0L));
	}
}
