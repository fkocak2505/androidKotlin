package com.onedio.androidside.mvp.view.dashboard.profileFragment.recylerAdapter

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.mvp.model.dashboard.model.ContentList


class ProfileContentRecylerAdapter2(val context: Context, val contentList: MutableList<ContentList>, val listener: (Int) -> Unit) :
    RecyclerView.Adapter<ProfileContentRecylerAdapter2.ModelViewHolder>() {

    class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contentImage: ImageView = view.findViewById(R.id.contentImage)
        val contentTitle: TextView = view.findViewById(R.id.contentTitle)
        val contentDate: TextView = view.findViewById(R.id.contentDate)
        //val contentComment: TextView = view.findViewById(R.id.contentComment)


        fun bindItems(context: Context, item: ContentList, pos: Int, listener: (Int) -> Unit) {
            //contentImage.setImageResource(item.contentImage)

            Picasso.get().load(item.contentImage)
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
                        contentImage.setImageBitmap(imageRounded)
                    }
                })

            contentTitle.typeface = OnedioCommon.changeTypeFace4Activity(context)
            contentDate.typeface = OnedioCommon.changeTypeFace4Activity(context)

            contentTitle.setText(item.contentTitle)
            contentDate.setText(item.contentDate)
            //contentComment.setText(item.contentComment)


            itemView.setOnClickListener {
                listener(pos)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.profile_list_view_item, parent, false)
        return ModelViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return contentList.size
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.bindItems(context, contentList.get(position), position, listener)
    }

}