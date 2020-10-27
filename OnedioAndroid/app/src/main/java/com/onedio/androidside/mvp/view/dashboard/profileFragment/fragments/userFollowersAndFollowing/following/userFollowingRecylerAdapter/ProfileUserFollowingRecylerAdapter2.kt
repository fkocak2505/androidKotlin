package com.onedio.androidside.mvp.view.dashboard.profileFragment.fragments.userFollowersAndFollowing.following.userFollowingRecylerAdapter

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
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.dashboard.profil.UsersProfilData
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ProfileUserFollowingRecylerAdapter2(
    val context: Context,
    val mListOfFollowers: MutableList<UsersProfilData>,
    val listener: (UsersProfilData, Int) -> Unit
) :
    RecyclerView.Adapter<ProfileUserFollowingRecylerAdapter2.ModelViewHolder>() {

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


        fun bindItems(
            context: Context,
            item: UsersProfilData,
            pos: Int,
            listener: (UsersProfilData, Int) -> Unit
        ) {
            /*Picasso.get().load(item.avatarUrl)
                .into(object : Target {
                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

                    }

                    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {

                    }

                    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {

                        val imageRounded = Bitmap.createBitmap(bitmap!!.width, bitmap!!.height, bitmap!!.config)
                        val canvas = Canvas(imageRounded)
                        val mpaint = Paint()
                        mpaint.setAntiAlias(true)
                        mpaint.setShader(BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP))
                        canvas.drawRoundRect(
                            RectF(0f, 0f, bitmap!!.width.toFloat(), bitmap!!.height.toFloat()),
                            100f,
                            100f,
                            mpaint
                        )// Round Image Corner 100 100 100 100
                        userFollowerProfilePic.setImageBitmap(imageRounded)
                    }
                }) */

            item.image?.let {
                Picasso.get().load(item.image?.alternates?.standardResolution?.url)
                    .into(userFollowerProfilePic, object : Callback {
                        override fun onSuccess() {
                            follewerProfilePicProgress.visibility = View.GONE
                        }

                        override fun onError(e: Exception?) {
                            follewerProfilePicProgress.visibility = View.GONE
                            userFollowerProfilePic.setImageResource(R.drawable.empty_avatar)
                        }
                    })
            } ?: run {
                follewerProfilePicProgress.visibility = View.GONE
            }


            userFollowerUserName.setText(item.username)
            userFollowerUserRealName.setText(item.name)
            //contentComment.setText(item.contentComment)

            userFollowerUserName.typeface = OnedioCommon.changeTypeFace4Activity(context)
            userFollowerUserRealName.typeface = OnedioCommon.changeTypeFace4Activity(context)
            followButton.typeface = OnedioCommon.changeTypeFace4Activity(context)

            itemView.setOnClickListener {
                listener(item, pos)
            }

            val sharedPref = context.getSharedPreferences(
                OnedioConstant.SHARED_PREF_FILE_NAME,
                Context.MODE_PRIVATE
            )
            val theme = sharedPref?.getString("mode", "default")!!

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