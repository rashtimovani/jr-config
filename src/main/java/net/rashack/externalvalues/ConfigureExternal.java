package net.rashack.externalvalues;

import net.rashack.externalvalues.exceptions.ExternalBuiltException;
import net.rashack.externalvalues.exceptions.InvalidKeyException;

/**
 * Flow API based configurator for easier set up of single{@link External}
 * variable.
 *
 * @param <T>
 *            value type which will {@link External} variable return.
 */
public interface ConfigureExternal<T> {

	/**
	 * Configures key for {@link External} variable over which that variable
	 * could find its value in external resource. Since key will be used for
	 * identifying values, it must not have white spaces in it. Returns built
	 * external that will read value over provided key from resources.
	 *
	 * @throws InvalidKeyException
	 *             if provided key is null, or contains white spaces
	 * @throws ExternalBuiltException
	 *             if this object was already used to create {@link External}
	 *             variable.
	 */
	External<T> buildForKey(String key);

	/**
	 * Sets default value for {@link External} variable. {@link External}
	 * variable will provide this default value when value in external resources
	 * is not found.
	 */
	ConfigureExternal<T> defaultValue(T value);
}
