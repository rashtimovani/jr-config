package net.rashack.externalvalues.convert;

/**
 * Provides ways to convert value obtained from external resource to specified
 * type.
 * 
 * @param <T>
 *            type to which will value from external resource be converted.
 */
@FunctionalInterface
public interface ExternalConverter<T> {

	/**
	 * Converts textual representation of value from external resource to
	 * specified type.
	 */
	T convert(String valueFromResource);
}
