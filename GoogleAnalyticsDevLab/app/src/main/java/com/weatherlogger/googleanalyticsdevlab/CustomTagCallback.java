package com.weatherlogger.googleanalyticsdevlab;

import android.util.Log;

import com.google.android.gms.tagmanager.Container;

import java.util.Map;

class CustomTagCallback implements Container.FunctionCallTagCallback {
    @Override
    public void execute(String tagName, Map<String, Object> parameters) {
        // The code for firing this custom tag.
        Log.i("CuteAnimals", "Custom function call tag :" + tagName + " is fired.");
    }
}