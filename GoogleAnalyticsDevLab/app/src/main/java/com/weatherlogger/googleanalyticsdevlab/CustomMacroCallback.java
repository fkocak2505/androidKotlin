package com.weatherlogger.googleanalyticsdevlab;

import com.google.android.gms.tagmanager.Container;

import java.util.Map;

class CustomMacroCallback implements Container.FunctionCallMacroCallback {
    private int numCalls;

    @Override
    public Object getValue(String name, Map<String, Object> parameters) {
        if ("increment".equals(name)) {
            return ++numCalls;
        } else if ("mod".equals(name)) {
            return (Long) parameters.get("key1") % Integer.valueOf((String) parameters.get("key2"));
        } else {
            throw new IllegalArgumentException("Custom macro name: " + name + " is not supported.");
        }
    }
}
