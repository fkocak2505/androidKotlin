package com.weatherlogger.googleanalyticsdevlab

/**
 * Singleton to hold the GTM Container (since it should be only created once
 * per run of the app).
 */
import com.google.android.gms.tagmanager.ContainerHolder

/**
 * Singleton to hold the GTM Container (since it should be only created once
 * per run of the app).
 */
object ContainerHolderSingleton {
    var containerHolder: ContainerHolder? = null
}
/**
 * Utility class; don't instantiate.
 */