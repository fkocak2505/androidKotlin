package com.musicore.nestedrecyclerview.holder

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.musicore.nestedrecyclerview.R
import com.musicore.nestedrecyclerview.holder.commentAdapter.CommentAdapter
import com.musicore.nestedrecyclerview.model.ArticleDetailAdapterModel
import com.musicore.nestedrecyclerview.model.CommentsItem

class HolderItemComment(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewPool = RecyclerView.RecycledViewPool()

    private var articleDetailCommentsRecyclerView: RecyclerView? =
        itemView.findViewById(R.id.articleDetailCommentsRecyclerView) as RecyclerView

    private var seeAllComments: Button? =
        itemView.findViewById(R.id.seeAllComments) as Button

    private var infoFirstComment: TextView? =
        itemView.findViewById(R.id.infoFirstComment) as TextView

    private var writeCommentArea: EditText? =
        itemView.findViewById(R.id.writeCommentArea) as EditText


    fun bindItems(
        context: Context,
        item: ArticleDetailAdapterModel,
        listener: (Int, ArticleDetailAdapterModel, String) -> Unit,
        pos: Int
    ) {

        if (item.data.data?.comments?.size != 0) {
            seeAllComments?.visibility = View.VISIBLE
            articleDetailCommentsRecyclerView?.visibility = View.VISIBLE
            infoFirstComment?.visibility = View.INVISIBLE
            writeCommentArea?.visibility = View.INVISIBLE

            item.data.data?.let {
                it.stats?.let {
                    it.comments?.let {
                        seeAllComments?.text = it.toString()
                    } ?: run {
                        seeAllComments?.text = "0"
                    }
                } ?: run {
                    seeAllComments?.text = "0"
                }
            } ?: run {
                seeAllComments?.text = "0"
            }

            val childLayoutManager =
                LinearLayoutManager(
                    articleDetailCommentsRecyclerView?.context,
                    RecyclerView.VERTICAL,
                    false
                )
            childLayoutManager.initialPrefetchItemCount = 10
            articleDetailCommentsRecyclerView?.apply {
                layoutManager = childLayoutManager
                adapter = CommentAdapter(
                    articleDetailCommentsRecyclerView?.context!!,
                    item.data.data?.comments!!
                ) { pos, itemOfComments, viewType ->
                    handleClickViewsComment(context, pos, itemOfComments, viewType)
                }
                setRecycledViewPool(viewPool)
            }
        } else {
            seeAllComments?.visibility = View.INVISIBLE
            articleDetailCommentsRecyclerView?.visibility = View.INVISIBLE
            infoFirstComment?.visibility = View.VISIBLE
            writeCommentArea?.visibility = View.VISIBLE
        }
    }

    private fun handleClickViewsComment(
        context: Context,
        pos: Int,
        itemOfComment: CommentsItem,
        viewType: String
    ) {
        when (viewType) {
            "PROFILE" -> {
                Toast.makeText(context, itemOfComment.text + " " + viewType, Toast.LENGTH_SHORT)
                    .show()
            }
            "LIKE" -> {
                Toast.makeText(
                    context,
                    itemOfComment.ratings?.likes.toString() + " " + viewType,
                    Toast.LENGTH_SHORT
                ).show()
            }
            "COMMENT" -> {
                Toast.makeText(context, "Comment  $viewType", Toast.LENGTH_SHORT).show()
            }
        }
    }


}