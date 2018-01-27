package net.rashack.externalvalues;

import net.rashack.externalvalues.convert.ExternalConverter;
import net.rashack.externalvalues.exceptions.NoConverterException;
import net.rashack.externalvalues.implementation.ManipulateExternals;

/**
 * Entry point class for handling with {@link External} values.
 */
public final class Externals {
	private static final Externals INSTANCE = new Externals();

	/**
	 * Provides ways to configure {@link External} variable with custom
	 * converter which will parse loaded value to specified type.
	 *
	 * @throws NoConverterException
	 *             if provided converter is null.
	 */
	public static <T> ConfigureExternal<T> converted(final ExternalConverter<T> converter) {
		return INSTANCE.manipulateExternals.converted(converter);
	}

	/**
	 * Sets all created {@link External} object to read external values from
	 * provided value provided over keys those {@link External} objects are
	 * associated with.
	 */
	public static void readFromValues(final ValueProvider valueProvider) {
		INSTANCE.manipulateExternals.readFromValues(valueProvider);
	}

	/**
	 * Provides ways to configure {@link External} variable that will provide
	 * values of T type.
	 *
	 * @throws NoConverterException
	 *             if provided type doesn't have predefined converter. If this
	 *             happens, use {@link #converted(ExternalConverter)} method to
	 *             configure external instead of this method.
	 */
	public static <T> ConfigureExternal<T> typed(final Class<T> type) {
		return INSTANCE.manipulateExternals.typed(type);
	}

	private final ManipulateExternals manipulateExternals = ManipulateExternals.produce();

	private Externals() {}
}
