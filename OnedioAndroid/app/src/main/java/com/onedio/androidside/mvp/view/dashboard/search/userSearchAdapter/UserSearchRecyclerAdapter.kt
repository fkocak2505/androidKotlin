package com.onedio.androidside.mvp.view.dashboard.search.userSearchAdapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_search_data_item.view.*
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.SearchListDataModel

class UserSearchRecyclerAdapter(
    private val context: Context,
    private val items: MutableList<SearchListDataModel>,
    private val listener: (SearchListDataModel) -> Unit
) :
    RecyclerView.Adapter<UserSearchRecyclerAdapter.ViewHoldere>() {


    fun addItems(items: MutableList<SearchListDataModel>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        p1: Int
    ): ViewHoldere {
        return ViewHoldere(
            LayoutInflater.from(parent.context).inflate(
                R.layout.user_search_data_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHoldere, position: Int) {

        loadMostTrendingCoverPhoto(
            items[position].imageUrl!!,
            holder.userIcon
        )

        holder.userSearchedName.text = items[position].text

        holder.userSearchedName.typeface = OnedioCommon.changeTypeFace4Activity(context)

        holder.userSearchConstraint.setOnClickListener{
            listener(items[position])
        }

        val sharedPref =
            context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref.getString("mode", "default")!!
        if(theme == "dark"){
            holder.userSearchedName.setTextColor(Color.parseColor("#FFFFFF"))
        }else{
            holder.userSearchedName.setTextColor(Color.parseColor("#191919"))
        }

    }

    private fun loadMostTrendingCoverPhoto(
        coverPhoto: String,
        imageView: ImageView
    ) {
        if (coverPhoto.contains("gif", true))
            loadGifWithGlide(
                context,
                coverPhoto,
                imageView
            )
        else
            loadImageWithProgress(coverPhoto, imageView)
    }

    private fun loadImageWithProgress(
        imageUrl: String,
        imageView: ImageView
    ) {
        Picasso.get().load(imageUrl)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    imageView.visibility = View.VISIBLE
                }

                override fun onError(e: Exception?) {
                    imageView.visibility = View.VISIBLE
                    imageView.setImageResource(R.drawable.image_error_dark_mode)
                }
            })
    }

    private fun loadGifWithGlide(
        context: Context,
        gifUrl: String,
        imageView: ImageView
    ) {
        Glide.with(context)
            .load(gifUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    imageView.visibility = View.VISIBLE
                    imageView.setImageResource(R.drawable.image_error_dark_mode)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    imageView.visibility = View.VISIBLE
                    return false
                }
            })
            .into(imageView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHoldere(view: View) : RecyclerView.ViewHolder(view) {
        val userSearchedName: TextView = view.userSearchedName
        val userIcon: ImageView = view.userIcon
        val userSearchConstraint: ConstraintLayout = view.userSearchConstraint

    }


}