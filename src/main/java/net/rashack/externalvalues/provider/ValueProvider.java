package net.rashack.externalvalues.provider;

import java.util.Optional;

/**
 * Interface that provides bridge between external object instance and external
 * resources file. External object will call {@link #forKey(String)} method with
 * key associated to it to get textual representation of its value in external
 * resources.
 */
@FunctionalInterface
public interface ValueProvider {

	/**
	 * Returns value from external resources associated to provided key. Method
	 * returns {@link Optional} since it is not required that value for provided
	 * key to be present in external resources.
	 */
	Optional<String> forKey(String key);
}
