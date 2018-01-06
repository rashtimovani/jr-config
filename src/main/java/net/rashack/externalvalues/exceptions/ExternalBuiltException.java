package net.rashack.externalvalues.exceptions;

/**
 * Exception that is thrown when trying to build External using configurator
 * that already built external.
 */
public class ExternalBuiltException extends ExternalValueException {
	private static final long serialVersionUID = -3062614437359576863L;

	public ExternalBuiltException() {
		super("External was already instantiated, create new configurator to create new External!");
	}
}
