package net.rashack.externalvalues.implementation.convert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class TestPrimitiveBooleanConverter {

	@Test
	public void testEmptyConvertsToFalse() {
		assertThat(new PrimitiveBooleanConverter().convert(""), equalTo(false));
	}
}
