package com.eventbus.eventbusdev

import org.greenrobot.eventbus.EventBus


object GlobalBus {
    private var sBus: EventBus? = null
    val bus: EventBus?
        get() {
            sBus?.let {

            }?: run{
                sBus = EventBus.getDefault()
            }

            return sBus
        }
}
