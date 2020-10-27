package com.onedio.androidside.mvp.view.dashboard.categoryFragment.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ProgressBar
import com.onedio.androidside.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.onedio.androidside.mvp.model.dashboard.categoryFragment.categoryModel.CategoryModel

class BadgeCategoryGridViewAdapter(
    private var categoryListData: MutableList<CategoryModel>,
    var context: Context,
    var listener: (Int, CategoryModel) -> Unit
) : BaseAdapter() {

    override fun getItem(position: Int): Any {
        return categoryListData[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return categoryListData.size
    }


    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = inflator.inflate(R.layout.badge_category_grid_item, null)

        val badgeCategoryItemIcon = view.findViewById(R.id.badgeCategoryItemIcon) as ImageView
        val badgeCategoryItemIconProgress: ProgressBar =
            view.findViewById(R.id.badgeCategoryItemIconProgress) as ProgressBar

        if (categoryListData[position].image != "")
            loadImage(
                categoryListData[position].image!!,
                badgeCategoryItemIcon,
                badgeCategoryItemIconProgress
            )
        else
            badgeCategoryItemIconProgress.visibility = View.GONE


        view.setOnClickListener{
            listener(position, categoryListData[position])
        }


        return view
    }

    private fun loadImage(imageUrl: String, imageView: ImageView, progressBar: ProgressBar) {
        Picasso.get().load(imageUrl)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    progressBar.visibility = View.GONE
                    imageView.setImageResource(R.drawable.image_error_dark_mode)
                    /// Log. Errorr..
                }
            })
    }


}