package com.onedio.androidside.mvp.view.dashboard.search.gridAdapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.FeaturedNewsDataModel
import java.lang.Exception


class FeaturedNewsGridAdapter(
    var context: Context,
    var featuredNewsList: MutableList<FeaturedNewsDataModel>,
    private val listener: (FeaturedNewsDataModel) -> Unit
) : BaseAdapter() {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var myView = convertView
        var holder: ViewHolder = ViewHolder()


        val mInflater = LayoutInflater.from(context)
        myView = mInflater.inflate(R.layout.featured_news_grid_view_item, parent, false)

        holder.itemOfFeaturedNewsImage =
            myView.findViewById(R.id.itemOfFeaturedNewsImage) as ImageView
        holder.photoProgess = myView.findViewById(R.id.photoProgress) as ProgressBar
        holder.itemOfFeaturedNewsTitle =
            myView.findViewById(R.id.itemOfFeaturedNewsTitle) as TextView
        holder.itemOfFeaturedNewsConstraint = myView.findViewById(R.id.itemOfFeaturedNewsConstraint)

        holder.itemOfFeaturedNewsTitle!!.typeface = OnedioCommon.changeTypeFace4Activity(context)

        val sharedPref = context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref.getString("mode", "default")

        if(theme == "dark"){
            holder.itemOfFeaturedNewsTitle!!.setTextColor(Color.parseColor("#FFFFFF"))
        }else{
            holder.itemOfFeaturedNewsTitle!!.setTextColor(Color.parseColor("#191919"))
        }

/*.centerCrop()
            .resize(120, 80)
            .transform(RoundedTransformation(7f, 0f))*/

        if(featuredNewsList[position].featuredNewsImage != ""){
            Picasso.get().load(featuredNewsList[position].featuredNewsImage)
                .into(holder.itemOfFeaturedNewsImage, object : Callback {
                    override fun onSuccess() {
                        holder.photoProgess!!.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        holder.photoProgess!!.visibility = View.GONE
                        holder.itemOfFeaturedNewsImage!!.setImageResource(R.drawable.image_error_dark_mode)
                    }
                })
        }else{
            holder.photoProgess!!.visibility = View.GONE
            holder.itemOfFeaturedNewsImage!!.setImageResource(R.drawable.image_error_dark_mode)
        }


        holder.itemOfFeaturedNewsTitle!!.text = featuredNewsList[position].featuredNewsTitle

        holder.itemOfFeaturedNewsConstraint?.setOnClickListener{
            listener(featuredNewsList[position])
        }

        return myView

    }

    override fun getItem(p0: Int): Any {
        return featuredNewsList.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return featuredNewsList.size
    }

    class ViewHolder {

        var itemOfFeaturedNewsConstraint: ConstraintLayout? = null
        var itemOfFeaturedNewsImage: ImageView? = null
        var photoProgess: ProgressBar? = null
        var itemOfFeaturedNewsTitle: TextView? = null

    }
}