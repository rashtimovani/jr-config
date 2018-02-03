package net.rashack.externalvalues.implementation.convert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class TestPrimitiveIntConverter {

	@Test
	public void testEmptyValueConvertsToZero() {
		assertThat(new PrimitiveIntConverter().convert(""), equalTo(0));
	}
}
