package com.musicore.popupdialogandroid

import android.content.DialogInterface

interface CustomElegantActionListeners {
    /** Called when positive button is clicked.*/
    fun onPositiveListener(dialog: PopupClass)
    /** Called when negative button is clicked.*/
    fun onNegativeListener(dialog: PopupClass)
    /** Called when got it button is clicked.*/
    fun onGotItListener(dialog: PopupClass)
    /** Called when dialog is cancelled.*/
    fun onCancelListener(dialog: DialogInterface)
    /** Called when got it button is clicked.*/
    fun onCloseClicked(dialog: PopupClass)

}