package com.onedio.androidside.mvp.view.dashboard.search.userSearchAdapter


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.SearchListDataModel

class UserSearchListAdapter(
    val context: Context,
    private val userSearchData: MutableList<SearchListDataModel>,
    var listener: (SearchListDataModel) -> Unit
) :
    BaseAdapter() {

    @SuppressLint("ViewHolder")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {

        val layouInflator = LayoutInflater.from(context)
        val rowView = layouInflator.inflate(R.layout.user_search_data_item, null, true)

        val userSearchedName = rowView.findViewById(R.id.userSearchedName) as TextView
        val userIcon = rowView.findViewById(R.id.userIcon) as ImageView
        val userSearchConstraint = rowView.findViewById(R.id.userSearchConstraint) as ConstraintLayout


        userSearchedName.typeface = OnedioCommon.changeTypeFace4Activity(context)

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            userSearchedName.setTextColor(Color.parseColor("#FFFFFF"))
            userIcon.foreground =
                context.resources.getDrawable(R.drawable.custom_image_view_user_search_dark_mode)
        } else {
            userSearchedName.setTextColor(Color.parseColor("#191919"))
            userIcon.foreground =
                context.resources.getDrawable(R.drawable.custom_image_view_user_search)
        }

        var imageUrl: String
        userSearchData[position].imageUrl?.let {
            imageUrl = userSearchData[position].imageUrl!!

            if (imageUrl != "") {
                Picasso.get().load(imageUrl)
                    .into(userIcon, object : Callback {
                        override fun onSuccess() {

                        }

                        override fun onError(e: Exception?) {
                            userIcon.setImageResource(R.drawable.empty_avatar)
                        }
                    })
            } else
                userIcon.setImageResource(R.drawable.empty_avatar)

        } ?: run {
            imageUrl = ""
            userIcon.setImageResource(R.drawable.empty_avatar)
        }

        userSearchConstraint.setOnClickListener{
            listener(userSearchData[position])
        }

        userSearchedName.text = userSearchData[position].text

        return rowView
    }

    override fun getItem(p0: Int): Any {
        return userSearchData[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return userSearchData.size
    }

}
