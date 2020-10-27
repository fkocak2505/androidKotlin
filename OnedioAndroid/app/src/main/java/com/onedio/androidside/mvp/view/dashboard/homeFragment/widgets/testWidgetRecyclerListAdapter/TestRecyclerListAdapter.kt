package com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.testWidgetRecyclerListAdapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.testWidgetRecyclerListAdapter.model.TestWidgetModel

class TestRecyclerListAdapter(
    val context: Context,
    val testList: MutableList<TestWidgetModel>,
    private val listener: (String, TestWidgetModel) -> Unit
) :
    RecyclerView.Adapter<TestRecyclerListAdapter.TestWidgetModelViewHolder>() {

    class TestWidgetModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemTestImage: ImageView = view.findViewById(R.id.itemTestImage)
        val itemTestTitle: TextView = view.findViewById(R.id.itemTestTitle)
        val itemTestImageProgress: ProgressBar = view.findViewById(R.id.itemTestImageProgress)

        val testBadgeLineCovertPhoto: View = view.findViewById(R.id.testBadgeLineCovertPhoto)
        val testCoverPhotoBadgeImage: ImageView = view.findViewById(R.id.testCoverPhotoBadgeImage)

        fun bindItems(item: TestWidgetModel, pos: Int, context: Context, listener: (String, TestWidgetModel) -> Unit) {

            itemTestTitle.text = item.testTitle
            loadImage(item.imageURL, itemTestImage, itemTestImageProgress)

            itemTestTitle.typeface = OnedioCommon.changeTypeFace4Activity(context)
            itemTestTitle.setTextColor(Color.parseColor("#191919"))

            if(item.badgeId != "" && item.badgeIcon != ""){
                testBadgeLineCovertPhoto.visibility = View.VISIBLE
                testCoverPhotoBadgeImage.visibility = View.VISIBLE

                loadImageWithoutProgress(
                    item.badgeIcon,
                    testCoverPhotoBadgeImage
                )
            }else{
                testBadgeLineCovertPhoto.visibility = View.INVISIBLE
                testCoverPhotoBadgeImage.visibility = View.INVISIBLE
            }

            itemView.setOnClickListener {
                listener(OnedioConstant.GENERAL, item)
            }

            testBadgeLineCovertPhoto.setOnClickListener{
                listener(OnedioConstant.BADGE_ICON, item)
            }

            testCoverPhotoBadgeImage.setOnClickListener{
                listener(OnedioConstant.BADGE_ICON, item)
            }
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestWidgetModelViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.test_item_card, parent, false)
        return TestWidgetModelViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return testList.size
    }

    override fun onBindViewHolder(holder: TestWidgetModelViewHolder, position: Int) {
        holder.bindItems(testList.get(position), position, context, listener)
    }

}