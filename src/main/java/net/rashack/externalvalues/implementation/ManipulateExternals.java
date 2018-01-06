package net.rashack.externalvalues.implementation;

import net.rashack.externalvalues.ConfigureExternal;
import net.rashack.externalvalues.convert.ExternalConverter;
import net.rashack.externalvalues.exceptions.NoConverterException;
import net.rashack.externalvalues.implementation.configure.ConfigureConvertedExternal;
import net.rashack.externalvalues.implementation.convert.PredefinedConverters;

public final class ManipulateExternals {
	public static ManipulateExternals produce() {
		return new ManipulateExternals(PredefinedConverters.produce());
	}

	private final PredefinedConverters predefinedConverters;

	ManipulateExternals(final PredefinedConverters predefinedConverters) {
		this.predefinedConverters = predefinedConverters;
	}

	public <T> ConfigureExternal<T> typed(final Class<T> type) {
		final ExternalConverter<T> converter = predefinedConverters.forType(type);
		return new ConfigureConvertedExternal<>(converter);
	}

	public <T> ConfigureExternal<T> converted(final ExternalConverter<T> converter) {
		if (converter == null) {
			throw new NoConverterException("Cannot provide null converter!");
		}
		return new ConfigureConvertedExternal<>(converter);
	}
}
