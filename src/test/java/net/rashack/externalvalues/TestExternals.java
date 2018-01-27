package net.rashack.externalvalues;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.BeforeClass;
import org.junit.Test;

import net.rashack.externalvalues.implementation.values.InMemoryValues;

public class TestExternals {

	private static InMemoryValues values;

	@BeforeClass
	public static void setUpTest() {
		values = new InMemoryValues().registerValue("testConvertedExternal", Integer.toString(42))
				.registerValue("testTypedExternal", Integer.toString(333));
		Externals.readFromValues(values);
	}

	@Test
	public void testCreatingExternalWithConverter() {
		final External<Integer> converted = Externals.converted(Integer::valueOf).buildForKey("testConvertedExternal");

		assertThat(converted.value(), equalTo(42));
	}

	@Test
	public void testCreatingTypedExternal() {
		final External<Integer> converted = Externals.typed(Integer.class).buildForKey("testTypedExternal");

		assertThat(converted.value(), equalTo(333));
	}
}
