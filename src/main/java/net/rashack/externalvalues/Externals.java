package net.rashack.externalvalues;

import net.rashack.externalvalues.convert.ExternalConverter;
import net.rashack.externalvalues.exceptions.NoConverterException;
import net.rashack.externalvalues.implementation.ManipulateExternals;
import net.rashack.externalvalues.provider.ValueProviderFactory;

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
	 * specified providers. Providers are specified in form of URI:<br/>
	 * {@code type://specific/values/for/each/provider}.<br/>
	 * Last specified provider definition in list will have most priority. That
	 * means if all files have inside key called "keyForSomeValue", value will
	 * be read from last provider specified. If that provider doesn't contain
	 * value, it will be read from previous provider in list.<br/>
	 * For example, if method is called:<br />
	 * {@code Externals.readFrom("properties://default.properties",
	 * "properties://etc/app/custom.properties")}<br/>
	 * if {@code custom.properties} contains mapping
	 * {@code keyForSomeValue = 4}, external
	 * associated with {@code keyForSomeValue} will return 4. If
	 * {@code custom.properties}
	 * doesn't contain that key, it will be read from
	 * {@code default.properties}.
	 */
	public static void readFrom(final String... providerDefinitions) {
		INSTANCE.manipulateExternals.readFromValues(providerDefinitions);
	}

	/**
	 * Registers custom value provider to {@link Externals}. {@link Externals}
	 * come with predefined value parsers that can read from standard java
	 * properties ({@code "properties://filename.propries"}), and this method
	 * provides ways to give system your implementation. If you need to read
	 * from csv file on custom way, you can create {@code CSVProvider} class,
	 * and register it like this:<br />
	 * {@code Externals.registerValueProvider("csv", CSVProvider::new)}.<br />
	 * After that, you can call:<br />
	 * {@code Externals.readFrom("csv://filename.csv")}<br />
	 * and your external values will be read from that file, in way that you
	 * specified how.
	 */
	public static void registerValueProvider(final String providerType, final ValueProviderFactory providerFactory) {
		INSTANCE.manipulateExternals.registerValueProvider(providerType, providerFactory);
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
