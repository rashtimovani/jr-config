package net.rashack.externalvalues.exceptions;

/**
 * Invalid key was provided for external variable to configurator. Key was
 * either null or contained white spaces.
 */
public class InvalidKeyException extends ExternalValueException {
	private static final long serialVersionUID = -3062614437359576863L;

	public InvalidKeyException(final String message) {
		super(message);
	}
}
