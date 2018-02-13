package net.rashack.externalvalues.implementation.convert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNull.nullValue;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class TestStringConverter {

	@Parameters({
			"adasda", "a a a a a sdasd", "46456456", "ĆĆĆĆĆĆĆĆĆĆĆĆĆĆĆĆĆĆĆĆĆĆĆĆĆĆĆĆ", "!", "a\n\rb"
	})
	@Test
	public void testConverting(final String value) {
		assertThat(new StringConverter().convert(value), equalTo(value));
	}

	@Test
	public void testEmpty() {
		assertThat(new StringConverter().convert(""), equalTo(""));
	}

	@Test
	public void testNull() {
		assertThat(new StringConverter().convert(null), nullValue());
	}

	@Test
	public void testTrimmed() {
		assertThat(new StringConverter().convert("   sad  "), equalTo("   sad  "));
	}
}
