package com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.securityPolicy.userBlocked.blockedUserAdapter

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.mvp.model.noDesigned.blockedUser.responseModel.BlockedUser

class ProfileUserBlockedRecylerAdapter2(val context: Context, val mListOfFollowers: MutableList<BlockedUser>, val listener: (Int) -> Unit) :
    RecyclerView.Adapter<ProfileUserBlockedRecylerAdapter2.ModelViewHolder>() {

    class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userFollowerProfilePic: ImageView = view.findViewById(R.id.followerProfilePic)
        val userFollowerUserName: TextView = view.findViewById(R.id.userFollowerUserName)
        val userFollowerUserRealName: TextView = view.findViewById(R.id.userFollowerUserRealName)
        val removeBlocked: Button = view.findViewById(R.id.removeBlocked)
        //val contentComment: TextView = view.findViewById(R.id.contentComment)


        fun bindItems(context: Context, item: BlockedUser, pos: Int, listener: (Int) -> Unit) {
            //contentImage.setImageResource(item.contentImage)

            Picasso.get().load(item.valOfAvatar)
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
                })

            userFollowerUserName.typeface = OnedioCommon.changeTypeFace4Activity(context)
            userFollowerUserRealName.typeface = OnedioCommon.changeTypeFace4Activity(context)
            removeBlocked.typeface = OnedioCommon.changeTypeFace4Activity(context)


            userFollowerUserName.setText(item.valOfUserName)
            userFollowerUserRealName.setText(item.valOfName)
            //contentComment.setText(item.contentComment)


            itemView.setOnClickListener {
                listener(pos)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_blocked_list_item, parent, false)
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