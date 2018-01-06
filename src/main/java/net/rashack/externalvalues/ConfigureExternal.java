package net.rashack.externalvalues;

import java.security.InvalidKeyException;

import net.rashack.externalvalues.exceptions.ExternalBuiltException;
import net.rashack.externalvalues.exceptions.KeyNotSetException;

/**
 * Flow API based configurator for easier set up of single{@link External}
 * variable.
 *
 * @param <T>
 *            value type which will {@link External} variable return.
 */
public interface ConfigureExternal<T> {

	/**
	 * Sets default value for {@link External} variable. {@link External}
	 * variable will provide this default value when value in external resources
	 * is not found.
	 */
	ConfigureExternal<T> defaultValue(T value);

	/**
	 * Builds instance of {@link External} variable when all desired attributes
	 * of it are set. Can only be called once, after which this object is
	 * unusable. Key must be set before this method is called.
	 *
	 * @throws ExternalBuiltException
	 *             if this object was already used to create {@link External}
	 *             variable.
	 * @throws KeyNotSetException
	 *             if build was called before key was set
	 */
	External<T> instantiate() throws ExternalBuiltException, KeyNotSetException;

	/**
	 * Configures key for {@link External} variable over which that variable
	 * could find its value in external resource. Since key will be used for
	 * identifying values, it must not have white spaces in it.
	 *
	 * @throws InvalidKeyException
	 *             if provided key is null, or contains white spaces
	 */
	ConfigureExternal<T> key(String key) throws InvalidKeyException;
}
