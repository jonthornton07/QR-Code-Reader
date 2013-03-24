package com.thornton.k3spring;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Wrapper object for the shared preferences.  This allows for easy access without
 * every activity knowing how to access the file saved in personal storage.
 * @author JT
 *
 */
public class SharedPreferencesWrapper {

	/**Name of the file saved in stored preferences*/
	private static final String FILENAME = "prefs";

	/**Key of the name String saved in storage*/
	public static final String NAME_KEY = "name";

	/**String returned if no name is found*/
	public static final String DEFAULT_STRING = "";

	/**
	 * Saved a string to the shared preferences
	 * @param context - activity context
	 * @param key - key the string should be saved under
	 * @param string - string to save
	 */
	public static void saveString(final Context context, final String key, final String string){
		final SharedPreferences prefs = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
		final Editor editor = prefs.edit();
		editor.putString(key, string);
		editor.commit();
	}

	/**
	 * Get a string from shared preferences
	 * @param context - activity context
	 * @param key - key to get from the shared preferences
	 * @return the string retrieved from the shared preferences
	 */
	public static String getString(final Context context, final String key){
		final SharedPreferences prefs = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
		return prefs.getString(key, DEFAULT_STRING);
	}

}
