package parku.com.au.parku.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Celdane.Lansangan on 2/18/2016.
 */
public class SharedPrefManager {
    private static final String PREFS_NAME = "au.com.PARKU_PREFS";

    private SharedPreferences preferences;

    public SharedPrefManager(Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void putString(String key, String value) {
        checkForNullKey(key);
        checkForNullKey(value);
        preferences.edit().putString(key, value).apply();
    }

    /**
     * null keys would corrupt the shared pref file and make them unreadable this is a preventive measure
     * @param key String
     */
    public void checkForNullKey(String key){
        if (key == null){
            throw new NullPointerException();
        }
    }
}
