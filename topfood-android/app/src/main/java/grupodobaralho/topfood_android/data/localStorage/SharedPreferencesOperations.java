package grupodobaralho.topfood_android.data.localStorage;

import android.content.Context;
import android.content.SharedPreferences;

import grupodobaralho.topfood_android.TopfoodApplication;

// local storage utilizado em apps Android. Baseado no projeto adocoes.
public class SharedPreferencesOperations {

    private static final String PREFS_NAME = "SHARED_PREFS_TOPFOOD";

    public static final String ACCESS_TOKEN = "accessToken";

//    public static final String USER_ID = "userId";

    public static void saveOnPrefs(String key, String value){

        SharedPreferences settings = TopfoodApplication.getTopfoodApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void removeFromPrefs(String key){

        SharedPreferences settings = TopfoodApplication.getTopfoodApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(key);
        editor.apply();
    }

    public static String loadFromPrefs(String key){

        SharedPreferences settings = TopfoodApplication.getTopfoodApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, null);
    }


}
