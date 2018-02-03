package net.rashack.externalvalues.implementation.values;

import static java.lang.String.format;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
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
				throw new InvalidValueProviderException(format(
						"Unable to load properties from file: %s%nFor properties provider, filename from where to read"
								+ " is \"host" + File.separator + "path\" part of the uri!",
						loadFrom), e);
			}
		}
	}

	private final Properties properties = new Properties();
	private final Loader loader;

	public PropertiesProvider(final URI uri) {
		loader = new Loader(toFile(uri));
		loader.load(properties);
	}

	@Override
	public Optional<String> forKey(final String key) {
		return Optional.ofNullable(properties.getProperty(key));
	}

	private File toFile(final URI uri) {
		File file = null;
		if (uri.getHost() != null && !uri.getHost().isEmpty()) {
			file = new File(uri.getHost());
		}
		if (uri.getPath() != null && !uri.getPath().isEmpty()) {
			file = file != null ? new File(file, uri.getPath()) : new File(uri.getPath());
		}
		return file;
	}
}
