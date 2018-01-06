package net.rashack.externalvalues.exceptions;

/**
 * Converter for some type was not found.
 */
public class NoConverterException extends ExternalValueException {
	private static final long serialVersionUID = 4408974031800318406L;

	public NoConverterException(final String message) {
		super(message);
	}
}
