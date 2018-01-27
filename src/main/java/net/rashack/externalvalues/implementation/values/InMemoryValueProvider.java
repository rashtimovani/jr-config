package net.rashack.externalvalues.implementation.values;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.rashack.externalvalues.ValueProvider;

public class InMemoryValueProvider implements ValueProvider {
	private final ConcurrentMap<String, String> loadedValues = new ConcurrentHashMap<>();

	@Override
	public Optional<String> forKey(String key) {
		return Optional.of(loadedValues.get(key));
	}
}
