package net.rashack.externalvalues;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.BeforeClass;
import org.junit.Test;

import net.rashack.externalvalues.implementation.values.InMemoryProvider;

public class TestExternals {

	private static InMemoryProvider values;

	@BeforeClass
	public static void setUpTest() {
		values = new InMemoryProvider().registerValue("emptyProperty", Integer.toString(42))
				.registerValue("inMemoryExternal", Integer.toString(333));
		Externals.registerValueProvider("inMemory", uri -> values);

		Externals.readFrom("properties://src/test/resources/TestProperties.properties", "inMemory://test");
	}

	@Test
	public void testCreatingExternalWithConverter() {
		final External<Integer> converted = Externals.converted(Integer::valueOf).buildForKey("emptyProperty");

		assertThat(converted.value(), equalTo(42));
	}

	@Test
	public void testCreatingTypedExternal() {
		final External<Integer> converted = Externals.typed(Integer.class).buildForKey("inMemoryExternal");

		assertThat(converted.value(), equalTo(333));
	}

	@Test
	public void testCreatingTypedExternalFromProperties() {
		final External<Long> converted = Externals.typed(Long.class).buildForKey("longProperty");

		assertThat(converted.value(), equalTo(234234L));
	}
}
