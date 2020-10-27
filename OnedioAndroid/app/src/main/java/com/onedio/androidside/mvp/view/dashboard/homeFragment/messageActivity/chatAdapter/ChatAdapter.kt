package com.onedio.androidside.mvp.view.dashboard.homeFragment.messageActivity.chatAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.chatModel.Chat
import com.onedio.androidside.mvp.view.dashboard.homeFragment.messageActivity.chatAdapter.holder.ViewHolder
import com.onedio.androidside.mvp.view.dashboard.homeFragment.messageActivity.selectableAdapter.SelectableAdapter

class ChatAdapter(
    val context: Context,
    val arrayList: List<Chat>,
    val clickListener: ViewHolder.ClickListener
) : SelectableAdapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemViewLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.message_list_item, null)

        return ViewHolder(
            itemViewLayout,
            clickListener
        )

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = arrayList[position].mName

        if (isSelected(position)) {
            holder.checked.isChecked = true
            holder.checked.visibility = View.VISIBLE
            arrayList[position].isDelete = true
        } else {
            holder.checked.isChecked = false
            holder.checked.visibility = View.GONE
            arrayList[position].isDelete = false
        }

        holder.tvTime.text = arrayList[position].mTime

        if (!arrayList[position].mImage.take(1).equals("/") && !arrayList[position].mImage.equals(""))
            Picasso.get().load(arrayList[position].mImage)
                .into(holder.userPhoto, object : Callback {
                    override fun onSuccess() {
                        holder.photoProgress.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {

                    }
                })
        else
            holder.photoProgress.visibility = View.GONE
        /*holder.userPhoto.setImageResource(arrayList[position].mImage)
        holder.photoProgress.visibility = View.GONE*/



        if (arrayList[position].online)
            holder.onlineView.visibility = View.VISIBLE
        else
            holder.onlineView.visibility = View.INVISIBLE

        holder.tvLastChat.text = arrayList[position].mLastChat


        holder.tvName.typeface = OnedioCommon.changeTypeFace4Activity(context)
        holder.tvTime.typeface = OnedioCommon.changeTypeFace4Activity(context)
        holder.tvLastChat.typeface = OnedioCommon.changeTypeFace4Activity(context)

    }
}