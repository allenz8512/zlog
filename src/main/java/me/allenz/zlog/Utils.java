package me.allenz.zlog;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Utility tools.
 * 
 * @author Allenz
 * @since 0.1.0-RELEASE
 */
public class Utils {

	private Utils() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Consider if the string is empty.
	 * 
	 * @param str
	 *            String
	 * @return if the string is {@code null} or length equals 0 return
	 *         true,otherwise return false;
	 * @since 0.1.0-RELEASE
	 */
	public static boolean isEmpty(final String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * Get the length of the string by byte.
	 * 
	 * @param str
	 *            String
	 * @return the length of the string
	 */
	public static int getLength(final String str) {
		if (isEmpty(str)) {
			return 0;
		}
		int length = 0;
		for (int i = 0; i < str.length(); i++) {
			final int ascii = Character.codePointAt(str, i);
			if (ascii >= 0 && ascii <= 255) {
				length++;
			} else {
				length += 2;
			}
		}
		return length;
	}

	/**
	 * Get the integer value of static field the declared in specified class.
	 * 
	 * @param className
	 *            the fullname of the class
	 * @param fieldName
	 *            the name of the field
	 * @param defaultValue
	 *            the default value to return when failed to get value of the
	 *            field
	 * @return the value of the field when successed, or the default value when
	 *         failure
	 * @since 0.1.0-RELEASE
	 */
	public static int intReflectStaticFieldValue(final String className,
			final String fieldName, final int defaultValue) {
		try {
			final Class<?> clazz = Class.forName(className);
			final Field field = clazz.getDeclaredField(fieldName);
			if (Modifier.isStatic(field.getModifiers())
					&& field.getType().getName().equals("int")) {
				field.setAccessible(true);
				return field.getInt(null);
			}
		} catch (final Exception e) {
			LoggerFactory.getInternalLogger().verbose(e,
					"Can not get the value of %s.%s", className, fieldName);
		}
		return defaultValue;
	}

	/**
	 * Get the boolean value of static field the declared in specified class.
	 * 
	 * @param className
	 *            the fullname of the class
	 * @param fieldName
	 *            the name of the field
	 * @param defaultValue
	 *            the default value to return when failed to get value of the
	 *            field
	 * @return the value of the field when successed, or the default value when
	 *         failure
	 * @since 0.1.0-RELEASE
	 */
	public static boolean booleanReflectStaticFieldValue(
			final String className, final String fieldName,
			final boolean defaultValue) {
		try {
			final Class<?> clazz = Class.forName(className);
			final Field field = clazz.getDeclaredField(fieldName);
			if (Modifier.isStatic(field.getModifiers())
					&& field.getType().getName().equals("int")) {
				field.setAccessible(true);
				return field.getBoolean(null);
			}
		} catch (final Exception e) {
			LoggerFactory.getInternalLogger().verbose(e,
					"Can not get the value of %s.%s", className, fieldName);
		}
		return defaultValue;
	}
}
