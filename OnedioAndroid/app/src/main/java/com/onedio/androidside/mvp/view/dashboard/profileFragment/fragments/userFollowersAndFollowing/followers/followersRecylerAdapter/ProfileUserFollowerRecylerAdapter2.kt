package com.onedio.androidside.mvp.view.dashboard.profileFragment.fragments.userFollowersAndFollowing.followers.followersRecylerAdapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.dashboard.profil.UsersProfilData
import java.lang.Exception

class ProfileUserFollowerRecylerAdapter2(
    val context: Context,
    val mListOfFollowers: MutableList<UsersProfilData>,
    val listener: (UsersProfilData, Int) -> Unit
) :
    RecyclerView.Adapter<ProfileUserFollowerRecylerAdapter2.ModelViewHolder>() {

    fun addItems(items: MutableList<UsersProfilData>) {
        this.mListOfFollowers.addAll(items)
        notifyDataSetChanged()
    }


    class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userFollowerProfilePic: ImageView = view.findViewById(R.id.followerProfilePic)
        val userFollowerUserName: TextView = view.findViewById(R.id.userFollowerUserName)
        val userFollowerUserRealName: TextView = view.findViewById(R.id.userFollowerUserRealName)
        val followButton: Button = view.findViewById(R.id.followButton)
        val follewerProfilePicProgress: ProgressBar =
            view.findViewById(R.id.follewerProfilePicProgress)
        //val contentComment: TextView = view.findViewById(R.id.contentComment)


        fun bindItems(context: Context, item: UsersProfilData, pos: Int, listener: (UsersProfilData, Int) -> Unit) {

            item.image?.let {
                Picasso.get().load(item.image?.alternates?.standardResolution?.url).into(userFollowerProfilePic, object : Callback {
                    override fun onSuccess() {
                        follewerProfilePicProgress.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        follewerProfilePicProgress.visibility = View.GONE
                        userFollowerProfilePic.setImageResource(R.drawable.empty_avatar)
                    }
                })
            }?: run{
                follewerProfilePicProgress.visibility = View.GONE
            }

            userFollowerUserName.text = item.username
            userFollowerUserRealName.text = item.name

            userFollowerUserName.typeface = OnedioCommon.changeTypeFace4Activity(context)
            userFollowerUserRealName.typeface = OnedioCommon.changeTypeFace4Activity(context)
            followButton.typeface = OnedioCommon.changeTypeFace4Activity(context)

            //contentComment.setText(item.contentComment)


            itemView.setOnClickListener {
                listener(item, pos)
            }

            val sharedPref = context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
            val theme = sharedPref.getString("mode", "default")!!

            if(theme == "dark"){
                userFollowerProfilePic.foreground = ContextCompat.getDrawable(context, R.drawable.custom_image_view_white_dark_mode)
                userFollowerUserName.setTextColor(Color.parseColor("#FFFFFF"))
                userFollowerUserRealName.setTextColor(Color.parseColor("#FFFFFF"))
            }else{
                userFollowerProfilePic.foreground = ContextCompat.getDrawable(context, R.drawable.custom_image_view)
                userFollowerUserName.setTextColor(Color.parseColor("#191919"))
                userFollowerUserRealName.setTextColor(Color.parseColor("#191919"))
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_follower_list_item, parent, false)
        return ModelViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return mListOfFollowers.size
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.bindItems(context, mListOfFollowers.get(position), position, listener)
    }

}