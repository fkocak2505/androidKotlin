package com.example.chatuikotlin.conversation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatuikotlin.R

class HolderDate(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var date: TextView = itemView.findViewById(R.id.tv_date) as TextView

    fun getDate(): TextView? {
        return date
    }

    fun setDate(date: TextView) {
        this.date = date
    }

}