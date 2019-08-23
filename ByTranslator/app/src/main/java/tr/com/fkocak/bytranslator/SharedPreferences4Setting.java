package tr.com.fkocak.bytranslator;

/**
 * Created by fatihkocak on 10.10.2018.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import tr.com.fkocak.bytranslator.model.LanguageModel;
import tr.com.fkocak.bytranslator.model.Setting;

public class SharedPreferences4Setting {

    Gson gson = new Gson();
    Map<String,String> allPrefData = new HashMap<>();

    private SharedPreferences preferences;

    public SharedPreferences4Setting(Context appContext) {
        preferences = PreferenceManager.getDefaultSharedPreferences(appContext);
    }

    public void putListString(String key, ArrayList<String> stringList) {
        checkForNullKey(key);
        String[] myStringList = stringList.toArray(new String[stringList.size()]);
        preferences.edit().putString(key, TextUtils.join(",,", myStringList)).apply();
    }

    public ArrayList<String> getListString(String key) {
        return new ArrayList<String>(Arrays.asList(TextUtils.split(preferences.getString(key, ""), Pattern.quote(",,"))));
    }

    public void putString(String key, String value) {
        checkForNullKey(key);
        checkForNullValue(value);
        preferences.edit().putString(key, value).apply();
    }

    public String getString1(String key){
        return preferences.getString(key,"NO_KEY");
    }

    public String getString(String key) {
        return preferences.getString(key, gson.toJson(new Setting()));
    }

    public Map<String,String> getAllSData(){
        Map<String,?> keys = preferences.getAll();
        for(Map.Entry<String,?> entry : keys.entrySet()){
            allPrefData.put(entry.getKey(), entry.getValue().toString());
        }

        return allPrefData;

    }

    public void putBoolean(String key, boolean val){
        checkForNullKey(key);
        preferences.edit().putBoolean(key,val).apply();
    }

    public void getBoolean(String key){
        preferences.getBoolean(key,false);
    }


    public void checkForNullValue(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
    }

    public void checkForNullKey(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
    }
}