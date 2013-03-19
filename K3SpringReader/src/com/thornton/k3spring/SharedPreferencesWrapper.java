package com.thornton.k3spring;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferencesWrapper {

	private static final String FILENAME = "prefs";

	public static final String NAME_KEY = "name";

	public static final String DEFAULT_STRING = "";

	public static void saveString(final Context context, final String key, final String string){
		final SharedPreferences prefs = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
		final Editor editor = prefs.edit();
		editor.putString(key, string);
		editor.commit();
	}

	public static String getString(final Context context, final String key){
		final SharedPreferences prefs = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
		return prefs.getString(key, DEFAULT_STRING);
	}

}
