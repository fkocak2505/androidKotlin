package com.onedio.androidside.mvp.view.dashboard.categoryFragment.adapter

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
import androidx.core.content.ContextCompat
import com.onedio.androidside.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.dashboard.categoryFragment.categoryModel.CategoryModel

class CategoryGridViewAdapter(
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
        var view = inflator.inflate(R.layout.category_grid_item, null)

        val categoryGridItemConst = view.findViewById(R.id.categoryGridItemConst) as ConstraintLayout

        val categoryItemTitle = view.findViewById(R.id.categoryItemTitle) as TextView
        val categoryItemIcon = view.findViewById(R.id.categoryItemIcon) as ImageView
        val categoryItemIconProgress: ProgressBar =
            view.findViewById(R.id.categoryItemIconProgress) as ProgressBar

        categoryItemTitle.text = categoryListData[position].title
        if (categoryListData[position].image != "")
            loadImage(
                categoryListData[position].image!!,
                categoryItemIcon,
                categoryItemIconProgress
            )
        else
            categoryItemIconProgress.visibility = View.GONE

        categoryItemTitle.typeface = OnedioCommon.changeTypeFace4Activity(context)
        //categoryItemTitle.setTextColor(Color.parseColor("#231f20"))

        view.setOnClickListener{
            listener(position, categoryListData[position])
        }

        val sharedPref = context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref.getString("mode", "default")
        if(theme == "dark"){
            categoryItemTitle.setTextColor(Color.parseColor("#FFFFFF"))
            categoryGridItemConst.background = ContextCompat.getDrawable(context, R.drawable.layout_border_dark_mode)
        }else{
            categoryItemTitle.setTextColor(Color.parseColor("#231f20"))
            categoryGridItemConst.background = ContextCompat.getDrawable(context, R.drawable.layout_border)
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