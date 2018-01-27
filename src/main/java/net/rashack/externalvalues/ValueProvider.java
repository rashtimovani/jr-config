package net.rashack.externalvalues;

import java.util.Optional;

public interface ValueProvider {

	Optional<String> forKey(String key);
}
