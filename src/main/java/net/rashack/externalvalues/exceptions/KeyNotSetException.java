package net.rashack.externalvalues.exceptions;

/**
 * Key was not set to configurator before build.
 */
public class KeyNotSetException extends ExternalValueException {
	private static final long serialVersionUID = -2906293521555257607L;

	public KeyNotSetException() {
		super("Key was not set to configurator, first set key then instantiate External!");
	}
}
