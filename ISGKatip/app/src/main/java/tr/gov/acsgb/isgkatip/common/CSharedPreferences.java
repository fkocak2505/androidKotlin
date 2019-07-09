package tr.gov.acsgb.isgkatip.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CSharedPreferences {

    private SharedPreferences preferences;
    private Context context;

    //==============================================================================================
    public CSharedPreferences(Context mContext) {
        preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        this.context = mContext;
    }

    //==============================================================================================
    // Put String 4 SharedPreferences
    public void putString(String key, String value) {
        checkForNullKey(key);
        checkForNullValue(value);
        preferences.edit().putString(key, value).apply();
    }

    //==============================================================================================
    public String getString(String key) {
        return preferences.getString(key, "NoData");
    }

    //==============================================================================================
    /// Put List 4 SharedPreferences
    public void putListString(String key, ArrayList<String> stringList) {
        checkForNullKey(key);
        String[] myStringList = stringList.toArray(new String[stringList.size()]);
        preferences.edit().putString(key, TextUtils.join(",,", myStringList)).apply();
    }

    //==============================================================================================
    public ArrayList<String> getListString(String key) {
        return new ArrayList<String>(Arrays.asList(TextUtils.split(preferences.getString(key, ""), Pattern.quote(",,"))));
    }

    //==============================================================================================
    // Put List Dynamic Obj 4 Shared Pref
    public <T> void putListObj(String key, List<T> list) {
        Type type = new TypeToken<List<T>>() {
        }.getType();
        String json = new Gson().toJson(list, type);
        putString(key, json);
    }

    //==============================================================================================
    public <T> List<T> getListObj(String key, Class<T[]> tClass) {
        if (getString(key).equals("NoData"))
            return new ArrayList<>();

        final T[] jsonToObject = new Gson().fromJson(getString(key), tClass);
        return Arrays.asList(jsonToObject);
    }

    //==============================================================================================
    public void checkForNullValue(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
    }

    //==============================================================================================
    public void checkForNullKey(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
    }

    //==============================================================================================

}
