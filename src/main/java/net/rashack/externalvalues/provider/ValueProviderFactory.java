package net.rashack.externalvalues.provider;

import java.net.URI;

import net.rashack.externalvalues.exceptions.InvalidValueProviderException;

/**
 * Interface that provides way to build value providers for specified URI. These
 * are used when custom value provider needs to be registered to Externals code
 * stack.
 */
@FunctionalInterface
public interface ValueProviderFactory {

	/**
	 * Builds concrete instance of value provider for specified URI. Should
	 * never return null.
	 * 
	 * @throws InvalidValueProviderException
	 *             if anything goes wrong with building value providers
	 */
	ValueProvider forURI(URI uri);
}
