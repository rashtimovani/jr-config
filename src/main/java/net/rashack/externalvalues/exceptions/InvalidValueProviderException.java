package net.rashack.externalvalues.exceptions;

public class InvalidValueProviderException extends ExternalValueException {
	private static final long serialVersionUID = -3751592200000378003L;

	public static final String SPECIFICATION = "\nValue providers are specified in form of URIs, where:"
			+ ":\n\tSchema part specifies the provider uri\n\tOther parts of URI specify specific data for each provider type";

	public InvalidValueProviderException(final String message) {
		super(message + SPECIFICATION);
	}

	public InvalidValueProviderException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
