package com.onedio.androidside.mvp.view.dashboard.homeFragment.messageActivity.userSearchAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.chatModel.UserSearchChat

class UserSearchAdapter(
    val context: Context,
    val userSearchChatList: MutableList<UserSearchChat>,
    val listener: (Int) -> Unit
) :
    RecyclerView.Adapter<UserSearchAdapter.UserSearchViewHolder>() {

    class UserSearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvUserName: TextView = view.findViewById(R.id.tvUserName)
        val tvUserRealNameAndSurname: TextView = view.findViewById(R.id.tvUserRealNameAndSurname)
        val userProfilePhoto: ImageView = view.findViewById(R.id.userProfilePhoto)
        val photoProgress: ProgressBar = view.findViewById(R.id.photoProgress)

        fun bindItems(context: Context, item: UserSearchChat, pos: Int, listener: (Int) -> Unit) {
            tvUserName.setText(item.mName)
            tvUserRealNameAndSurname.setText(item.mRealNameAndSurname)

            if (item.mImage.take(1) != "/" && item.mImage != "")
                Picasso.get().load(item.mImage)
                    .into(userProfilePhoto, object : Callback {
                        override fun onSuccess() {
                            photoProgress.visibility = View.GONE
                        }

                        override fun onError(e: Exception?) {

                        }
                    })
            else
                photoProgress.visibility = View.GONE


            /*userProfilePhoto.setImageResource(item.mImage)
            photoProgress.visibility = View.GONE*/

            tvUserName.typeface = OnedioCommon.changeTypeFace4Activity(context)
            tvUserRealNameAndSurname.typeface = OnedioCommon.changeTypeFace4Activity(context)


            itemView.setOnClickListener {
                listener(pos)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSearchViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.user_chat_list_item, parent, false)
        return UserSearchViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return userSearchChatList.size
    }

    override fun onBindViewHolder(holder: UserSearchViewHolder, position: Int) {
        holder.bindItems(context, userSearchChatList[position], position, listener)
    }
}