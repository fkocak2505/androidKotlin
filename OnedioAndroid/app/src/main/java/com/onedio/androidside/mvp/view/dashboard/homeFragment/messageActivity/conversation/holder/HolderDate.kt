package com.onedio.androidside.mvp.view.dashboard.homeFragment.messageActivity.conversation.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.onedio.androidside.R

class HolderDate(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var date: TextView = itemView.findViewById(R.id.date) as TextView

    fun getDate(): TextView? {
        return date
    }

    fun setDate(date: TextView) {
        this.date = date
    }

}