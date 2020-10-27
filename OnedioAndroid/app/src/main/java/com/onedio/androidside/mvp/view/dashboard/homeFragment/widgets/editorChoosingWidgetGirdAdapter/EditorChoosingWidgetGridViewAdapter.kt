package com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.editorChoosingWidgetGirdAdapter

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
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.editorChoosingWidgetGirdAdapter.model.EditorChoosingWidgetGridModel

class EditorChoosingWidgetGridViewAdapter(
    private var editorChoosingWidgetListData: List<EditorChoosingWidgetGridModel>,
    var context: Context,
    var listener: (String, EditorChoosingWidgetGridModel) -> Unit
) : BaseAdapter() {

    override fun getItem(position: Int): Any {
        return editorChoosingWidgetListData[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return editorChoosingWidgetListData.size
    }

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflator.inflate(R.layout.four_widget_grid_row, null)

        val itemTitle = view.findViewById(R.id.itemTitle) as TextView
        val itemCoverPhoto = view.findViewById(R.id.itemCoverPhoto) as ImageView
        val itemCoverPhotoProgress: ProgressBar =
            view.findViewById(R.id.itemCoverPhotoProgress) as ProgressBar

        val badgeLineCovertPhotoEditorChoosing =
            view.findViewById(R.id.badgeLineCovertPhotoEditorChoosing) as View
        val coverPhotoBadgeImageEditorChoosing =
            view.findViewById(R.id.coverPhotoBadgeImageEditorChoosing) as ImageView

        itemTitle.text = editorChoosingWidgetListData[position].title

        if(editorChoosingWidgetListData[position].imageUrl != ""){
            loadImage(
                editorChoosingWidgetListData[position].imageUrl,
                itemCoverPhoto,
                itemCoverPhotoProgress
            )
        }else{
            itemCoverPhotoProgress.visibility = View.GONE
            itemCoverPhoto.setImageResource(R.drawable.image_error_dark_mode)
        }


        if (editorChoosingWidgetListData[position].badgeIcon != "" && editorChoosingWidgetListData[position].badgeId != "") {
            badgeLineCovertPhotoEditorChoosing.visibility = View.VISIBLE
            coverPhotoBadgeImageEditorChoosing.visibility = View.VISIBLE

            loadImageWithoutProgress(
                editorChoosingWidgetListData[position].badgeIcon!!,
                coverPhotoBadgeImageEditorChoosing
            )
        } else {
            badgeLineCovertPhotoEditorChoosing.visibility = View.INVISIBLE
            coverPhotoBadgeImageEditorChoosing.visibility = View.INVISIBLE
        }

        itemTitle.typeface = OnedioCommon.changeTypeFace4Activity(context)
        itemTitle.setTextColor(Color.parseColor("#231f20"))

        view.setOnClickListener {
            listener(OnedioConstant.GENERAL, editorChoosingWidgetListData[position])
        }

        badgeLineCovertPhotoEditorChoosing.setOnClickListener{
            listener(OnedioConstant.BADGE_ICON,editorChoosingWidgetListData[position])
        }


        coverPhotoBadgeImageEditorChoosing.setOnClickListener{
            listener(OnedioConstant.BADGE_ICON,editorChoosingWidgetListData[position])
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

    private fun loadImageWithoutProgress(imageUrl: String, imageView: ImageView) {
        Picasso.get().load(imageUrl)
            .into(imageView, object : Callback {
                override fun onSuccess() {

                }

                override fun onError(e: Exception?) {

                }
            })
    }

}