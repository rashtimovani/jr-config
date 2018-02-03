package net.rashack.externalvalues;

import java.net.URI;

/**
 * Interface that provides way to build value providers for specified URI.
 */
@FunctionalInterface
public interface ValueProviderFactory {

	/**
	 * Builds concrete instance of value provider for specified URI.
	 */
	ValueProvider forURI(URI uri);
}
