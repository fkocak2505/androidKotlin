package com.example.chatuikotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ChatAdapter(
    val context: Context,
    val arrayList: List<Chat>,
    val clickListener: ViewHolder.ClickListener
) : SelectableAdapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemViewLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_chat, null)

        return ViewHolder(itemViewLayout, clickListener)

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = arrayList[position].mName

        if(isSelected(position)){
            holder.checked.isChecked = true
            holder.checked.visibility = View.VISIBLE
            arrayList[position].isDelete = true
        }else{
            holder.checked.isChecked = false
            holder.checked.visibility = View.GONE
            arrayList[position].isDelete = false
        }

        holder.tvTime.text = arrayList[position].mTime
        holder.userPhoto.setImageResource(arrayList[position].mImage)

        if(arrayList[position].online)
            holder.onlineView.visibility = View.VISIBLE
        else
            holder.onlineView.visibility = View.INVISIBLE

        holder.tvLastChat.text = arrayList[position].mLastChat
    }
}