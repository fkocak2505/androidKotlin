package com.example.chatuikotlin

import android.view.Menu
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder (itemLayoutView: View, private val listener: ClickListener?) :
    RecyclerView.ViewHolder(itemLayoutView), View.OnClickListener, View.OnLongClickListener {

    var tvName: TextView
    var tvTime: TextView
    var tvLastChat: TextView
    var userPhoto: ImageView
    var online = false
    var onlineView: View
    var checked: CheckBox

    init {

        tvName = itemLayoutView.findViewById(R.id.tv_user_name) as TextView
        //selectedOverlay = (View) itemView.findViewById(R.id.selected_overlay);
        tvTime = itemLayoutView.findViewById(R.id.tv_time) as TextView
        tvLastChat = itemLayoutView.findViewById(R.id.tv_last_chat) as TextView
        userPhoto = itemLayoutView.findViewById(R.id.iv_user_photo) as ImageView
        onlineView = itemLayoutView.findViewById(R.id.online_indicator) as View
        checked = itemLayoutView.findViewById(R.id.chk_list) as CheckBox

        itemLayoutView.setOnClickListener(this)

        itemLayoutView.setOnLongClickListener(this)
    }

    override fun onClick(v: View) {
        listener?.onItemClicked(getAdapterPosition())
    }

    override fun onLongClick(view: View): Boolean {
        return listener?.onItemLongClicked(getAdapterPosition()) ?: false
    }

    interface ClickListener {
        fun onItemClicked(position: Int)

        fun onItemLongClicked(position: Int): Boolean

        fun onCreateOptionsMenu(menu: Menu): Boolean
    }
}