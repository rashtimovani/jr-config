package net.rashack.externalvalues.exceptions;

/**
 * Base exception class for all exceptions used in external values module.
 */
public class ExternalValueException extends RuntimeException {
	private static final long serialVersionUID = -9120131555924177892L;

	public ExternalValueException(final String message) {
		super(message);
	}

	public ExternalValueException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
