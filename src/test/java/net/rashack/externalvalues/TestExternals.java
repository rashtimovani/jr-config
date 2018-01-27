package net.rashack.externalvalues;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

import org.junit.Test;

import net.rashack.externalvalues.implementation.ConcreteExternal;

public class TestExternals {

	@Test
	public void testCreatingExternalWithConverter() {
		final External<Integer> converted = Externals.converted(Integer::valueOf).buildForKey("testExternal");

		assertThat(converted, instanceOf(ConcreteExternal.class));
	}

	@Test
	public void testCreatingTypedExternal() {
		final External<Integer> converted = Externals.typed(Integer.class).buildForKey("testExternal");

		assertThat(converted, instanceOf(ConcreteExternal.class));
	}
}
