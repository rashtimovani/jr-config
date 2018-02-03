package net.rashack.externalvalues.implementation.values;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

import net.rashack.externalvalues.ValueProvider;
import net.rashack.externalvalues.exceptions.InvalidValueProviderException;

public class PropertiesProvider implements ValueProvider {

	static class Loader {
		private final File loadFrom;

		private Loader(final File loadFrom) {
			this.loadFrom = loadFrom;
		}

		void load(final Properties properties) {
			try (InputStream fileStream = new FileInputStream(loadFrom)) {
				properties.load(fileStream);
			} catch (final IOException e) {
				throw new InvalidValueProviderException("Unable to load properties from file: " + loadFrom, e);
			}
		}
	}

	private final Properties properties = new Properties();
	private final Loader loader;

	public PropertiesProvider(final File loadFrom) {
		this(new Loader(loadFrom));
	}

	PropertiesProvider(final Loader loader) {
		this.loader = loader;
		this.loader.load(properties);
	}

	@Override
	public Optional<String> forKey(final String key) {
		return Optional.ofNullable(properties.getProperty(key));
	}
}
