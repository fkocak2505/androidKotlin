package com.onedio.androidside.mvp.view.dashboard.search.moreContent.adapter

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
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.FeaturedNewsDataModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class SearchAllContentDataGridAdapter(
    var context: Context,
    var featuredNewsList: MutableList<FeaturedNewsDataModel>,
    var listener: (FeaturedNewsDataModel) -> Unit
) : BaseAdapter() {

    var featuredFilteredNewsList: MutableList<FeaturedNewsDataModel> = mutableListOf()

    init {
        featuredFilteredNewsList = featuredNewsList
    }

    fun addItems(items: MutableList<FeaturedNewsDataModel>) {
        this.featuredNewsList.addAll(items)
        notifyDataSetChanged()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var myView = convertView
        var holder: ViewHolder


        val mInflater = LayoutInflater.from(context)
        myView = mInflater.inflate(R.layout.search_all_content_grid_view_item, parent, false)

        holder =
            ViewHolder()

        holder.itemOfAllContentSearchImage =
            myView.findViewById(R.id.itemOfAllContentSearchImage) as ImageView
        holder.photoProgess = myView.findViewById(R.id.photoProgress) as ProgressBar
        holder.itemOfAllContentSearchTitle =
            myView.findViewById(R.id.itemOfAllContentSearchTitle) as TextView
        holder.itemOfContentSearchDate =
            myView.findViewById(R.id.itemOfContentSearchDate) as TextView
        holder.moreContentConstraint = myView.findViewById(R.id.moreContentConstraint)


        /*.centerCrop()
            .resize(170, 100).transform(
                RoundedTransformation(7f, 0f)
            )*/

        Picasso.get().load(featuredNewsList[position].featuredNewsImage)
            .into(holder.itemOfAllContentSearchImage, object : Callback {
                override fun onSuccess() {
                    holder.photoProgess!!.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    holder.photoProgess!!.visibility = View.GONE
                }
            })

        holder.itemOfAllContentSearchTitle!!.text = featuredNewsList[position].featuredNewsTitle
        holder.itemOfContentSearchDate!!.text =
            OnedioCommon.changeDateFormat2Text(OnedioCommon.convertEpochTime2Date(featuredNewsList[position].featuredNewsDate))

        holder.itemOfAllContentSearchTitle!!.typeface =
            OnedioCommon.changeTypeFace4Activity(context)
        holder.itemOfContentSearchDate!!.typeface = OnedioCommon.changeTypeFace4Activity(context)


        val sharedPref =
            context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref.getString("mode", "default")!!
        if (theme == "dark") {
            holder.itemOfAllContentSearchTitle!!.setTextColor(Color.parseColor("#FFFFFF"))
            holder.itemOfContentSearchDate!!.setTextColor(Color.parseColor("#FFFFFF"))
        } else {
            holder.itemOfAllContentSearchTitle!!.setTextColor(Color.parseColor("#191919"))
            holder.itemOfContentSearchDate!!.setTextColor(Color.parseColor("#777777"))
        }

        holder.moreContentConstraint!!.setOnClickListener {
            listener(featuredNewsList[position])
        }

        return myView

    }

    override fun getItem(p0: Int): Any {
        return featuredFilteredNewsList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return featuredFilteredNewsList.size
    }

    class ViewHolder {

        var moreContentConstraint: ConstraintLayout? = null
        var itemOfAllContentSearchImage: ImageView? = null
        var photoProgess: ProgressBar? = null
        var itemOfAllContentSearchTitle: TextView? = null
        var itemOfContentSearchDate: TextView? = null

    }

}