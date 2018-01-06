package net.rashack.externalvalues;

import net.rashack.externalvalues.convert.ExternalConverter;
import net.rashack.externalvalues.exceptions.NoConverterException;
import net.rashack.externalvalues.implementation.ManipulateExternals;

/**
 * Entry point class for handling with {@link External} values.
 */
public final class Externals {
	private static final ManipulateExternals MANIPULATOR = ManipulateExternals.produce();

	/**
	 * Provides ways to configure {@link External} variable with custom
	 * converter which will parse loaded value to specified type.
	 *
	 * @throws NoConverterException
	 *             if provided converter is null.
	 */
	public static <T> ConfigureExternal<T> converted(final ExternalConverter<T> converter) throws NoConverterException {
		return MANIPULATOR.converted(converter);
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
	public static <T> ConfigureExternal<T> typed(final Class<T> type) throws NoConverterException {
		return MANIPULATOR.typed(type);
	}

	private Externals() {}
}
