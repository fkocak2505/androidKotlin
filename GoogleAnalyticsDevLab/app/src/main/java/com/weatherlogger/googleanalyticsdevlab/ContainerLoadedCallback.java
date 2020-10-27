package com.weatherlogger.googleanalyticsdevlab;

import com.google.android.gms.tagmanager.Container;
import com.google.android.gms.tagmanager.ContainerHolder;

class ContainerLoadedCallback implements ContainerHolder.ContainerAvailableListener {
    @Override
    public void onContainerAvailable(ContainerHolder containerHolder, String containerVersion) {
        // We load each container when it becomes available.
        Container container = containerHolder.getContainer();
        registerCallbacksForContainer(container);
    }

    public static void registerCallbacksForContainer(Container container) {
        // Register two custom function call macros to the container.
        container.registerFunctionCallMacroCallback("increment", new CustomMacroCallback());
        container.registerFunctionCallMacroCallback("mod", new CustomMacroCallback());
        // Register a custom function call tag to the container.
        container.registerFunctionCallTagCallback("custom_tag", new CustomTagCallback());
    }
}
