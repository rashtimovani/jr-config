package net.rashack.externalvalues;

/**
 * Interface for declaring fields over which external values can be obtained.
 *
 * @param <T>
 *            value type which will be obtained through call of {@link #value()}
 *            method.
 */
public interface External<T> {

	/**
	 * Returns value specified in external resource, parsed in appropriate format.
	 */
	T value();
}
