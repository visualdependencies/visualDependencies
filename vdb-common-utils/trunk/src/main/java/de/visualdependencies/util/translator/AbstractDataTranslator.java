package de.visualdependencies.util.translator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;

import edu.umd.cs.findbugs.annotations.NonNull;

/**
 * Abstract data map translator.
 * 
 * This utility class provides type-safe access to object properties.
 * 
 * @author Jan Philipp
 */
abstract public class AbstractDataTranslator {

	private static final String INVALID_DATA_VALUE = "Invalid data found for name \"%s\" (type \"%s\").";

	protected final Logger logger = Logger.getLogger(getClass());

	/**
	 * data storage (mutable)
	 */
	private final Map<String, String> data;

	private static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd-HH:mm:ss.S Z";

	private static final Locale DATE_FORMAT_LOCALE = Locale.ENGLISH;

	/**
	 * simple date formatter
	 */
	private final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_PATTERN, DATE_FORMAT_LOCALE);

	/**
	 * Creates a new translator with an empty map.
	 */
	public AbstractDataTranslator() {
		this(new HashMap<String, String>());
	}

	/**
	 * Creates a new translator with a given map.
	 * 
	 * @param data
	 */
	public AbstractDataTranslator(@NonNull final Map<String, String> data) {
		this.data = data;
	}

	/**
	 * Returns true if any value is stored by the specified name.
	 * 
	 * @param name
	 * @return
	 */
	final protected boolean contains(@NonNull final String name) {
		return data.containsKey(name);
	}

	/**
	 * Debugs all data into the info logger.
	 */
	final public void debug() {
		for (final Map.Entry<String, String> entry : data.entrySet()) {
			logger.debug(String.format("%s: %s = %s", getClass(), entry.getKey(), entry.getValue()));
		}
	}

	/**
	 * Returns the raw value stored by the specified name.
	 * 
	 * @internal
	 * @param name
	 * @return
	 */
	final protected String get(@NonNull final String name) {
		return data.get(name);
	}

	/**
	 * Returns the date value stored by the specified name.
	 * 
	 * Returns null if no value was stored or the value is invalid.
	 * 
	 * @param name
	 * @return
	 */
	final public Date getDate(@NonNull final String name) {
		try {
			return dateFormat.parse(get(name));
		} catch (final ParseException e) {
			logger.warn(String.format(INVALID_DATA_VALUE, name, Date.class));
		} catch (final NullPointerException e) {
			if (logger.isDebugEnabled()) {
				logger.debug(String.format(INVALID_DATA_VALUE, name, Date.class));
			}
		}
		return null;
	}

	/**
	 * Returns the double value stored by the specified name.
	 * 
	 * Returns null if no value was stored or the value is invalid.
	 * 
	 * @param name
	 * @return
	 */
	final public Double getDouble(@NonNull final String name) {
		if (contains(name)) {
			try {
				return Double.parseDouble(get(name));
			} catch (final NumberFormatException e) {
				logger.warn(String.format(INVALID_DATA_VALUE, name, Double.class));
			} catch (final NullPointerException e) {
				if (logger.isDebugEnabled()) {
					logger.debug(String.format(INVALID_DATA_VALUE, name, Double.class));
				}
			}
		}
		return null;
	}

	/**
	 * Returns the float value stored by the specified name.
	 * 
	 * Returns null if no value was stored or the value is invalid.
	 * 
	 * @param name
	 * @return
	 */
	final public Float getFloat(@NonNull final String name) {
		if (contains(name)) {
			try {
				return Float.parseFloat(get(name));
			} catch (final NumberFormatException e) {
				logger.warn(String.format(INVALID_DATA_VALUE, name, Float.class));
			} catch (final NullPointerException e) {
				if (logger.isDebugEnabled()) {
					logger.debug(String.format(INVALID_DATA_VALUE, name, Float.class));
				}
			}
		}
		return null;
	}

	/**
	 * Returns the integer value stored by the specified name.
	 * 
	 * Returns null if no value was stored or the value is invalid.
	 * 
	 * @param name
	 * @return
	 */
	final public Integer getInteger(@NonNull final String name) {
		if (contains(name)) {
			try {
				return Integer.parseInt(get(name));
			} catch (final NumberFormatException e) {
				logger.warn(String.format(INVALID_DATA_VALUE, name, Integer.class));
			} catch (final NullPointerException e) {
				if (logger.isDebugEnabled()) {
					logger.debug(String.format(INVALID_DATA_VALUE, name, Integer.class));
				}
			}
		}
		return null;
	}

	/**
	 * Returns the long value stored by the specified name.
	 * 
	 * Returns null if no value was stored or the value is invalid.
	 * 
	 * @param name
	 * @return
	 */
	final public Long getLong(@NonNull final String name) {
		if (contains(name)) {
			try {
				return Long.parseLong(get(name));
			} catch (final NumberFormatException e) {
				logger.warn(String.format(INVALID_DATA_VALUE, name, Long.class));
			} catch (final NullPointerException e) {
				if (logger.isDebugEnabled()) {
					logger.debug(String.format(INVALID_DATA_VALUE, name, Long.class));
				}
			}
		}
		return null;
	}

	/**
	 * Returns the string value stored by the specified name.
	 * 
	 * @param name
	 * @return
	 */
	final public String getString(@NonNull final String name) {
		if (contains(name)) {
			return get(name);
		} else {
			return null;
		}
	}

	/**
	 * Stores the value by the specified name.
	 * 
	 * @internal
	 * @param name
	 * @param value
	 */
	final protected void set(@NonNull final String name, final String value) {
		data.put(name, value);
	}

	/**
	 * Stores the date value by the specified name.
	 * 
	 * An invalid value will replaced with null.
	 * 
	 * @param name
	 * @param value
	 */
	final public void setDate(@NonNull final String name, final Date value) {
		if (value != null) {
			set(name, dateFormat.format(value));
		} else {
			set(name, null);
		}
	}

	/**
	 * Stores the double value by the specified name.
	 * 
	 * An invalid value will replaced with null.
	 * 
	 * @param name
	 * @param value
	 */
	final public void setDouble(@NonNull final String name, final Double value) {
		if (value != null) {
			set(name, Double.toString(value));
		} else {
			set(name, null);
		}
	}

	/**
	 * Stores the float value by the specified name.
	 * 
	 * An invalid value will replaced with null.
	 * 
	 * @param name
	 * @param value
	 */
	final public void setFloat(@NonNull final String name, final Float value) {
		if (value != null) {
			set(name, Float.toString(value));
		} else {
			set(name, null);
		}
	}

	/**
	 * Stores the integer value by the specified name.
	 * 
	 * An invalid value will replaced with null.
	 * 
	 * @param name
	 * @param value
	 */
	final public void setInteger(@NonNull final String name, final Integer value) {
		if (value != null) {
			set(name, Integer.toString(value));
		} else {
			set(name, null);
		}
	}

	/**
	 * Stores the long value by the specified name.
	 * 
	 * An invalid value will replaced with null.
	 * 
	 * @param name
	 * @param value
	 */
	final public void setLong(@NonNull final String name, final Long value) {
		if (value != null) {
			set(name, Long.toString(value));
		} else {
			set(name, null);
		}
	}

	/**
	 * Stores the string value by the specified name.
	 * 
	 * @param name
	 * @param value
	 */
	final public void setString(@NonNull final String name, final String value) {
		set(name, value);
	}

}
