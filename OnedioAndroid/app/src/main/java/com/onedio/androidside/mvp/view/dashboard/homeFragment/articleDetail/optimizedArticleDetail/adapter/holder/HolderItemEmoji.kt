package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.adapter.holder

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.emojiReaction.EmojiReactionModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.ArticleReactionsModel
import com.onedio.androidside.mvp.presenter.dashboard.homeFragment.articleDetail.ArticleDetailActivirtyPresenterImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.adapter.holder.emojiAdapter.EmojiReactionAdapter
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.model.ArticleDetailAdapterModel
import com.onedio.androidside.prefs.StringSharedPrefs

class HolderItemEmoji(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewPool = RecyclerView.RecycledViewPool()

    private var emojiReactionsRecyclerView: RecyclerView? =
        itemView.findViewById(R.id.itemOfEmojiReactionsRecyclerView) as RecyclerView

    private var itemOfInfoViewLine: View? =
        itemView.findViewById(R.id.itemOfInfoViewLine) as View

    private var emojiReactions: MutableList<ArticleReactionsModel> = mutableListOf()

    private var legacyId: Long = 0

    private lateinit var newMapOfEmojiReactionSharedPrefs: HashMap<String, MutableList<String>>

    fun bindItems(
        context: Context,
        item: ArticleDetailAdapterModel,
        listener: (Int, ArticleDetailAdapterModel, String) -> Unit,
        pos: Int,
        articleDetailActivirtyPresenterImpl: ArticleDetailActivirtyPresenterImpl
    ) {

        val sharedPref =
            context.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref.getString("mode", "default")!!

        if (theme == "dark") {
            itemOfInfoViewLine?.setBackgroundColor(Color.parseColor("#0e1720"))
        }else{
            itemOfInfoViewLine?.setBackgroundColor(Color.parseColor("#e2e2e2"))
        }

        item.data.data?.let {
            it.reactions?.let {
                emojiReactions = it
            } ?: run {
                emojiReactions = mutableListOf()
            }

            it.legacyId?.let {
                legacyId = it
            } ?: run {
                legacyId = 0
            }

        } ?: run {
            emojiReactions = mutableListOf()
            legacyId = 0
        }

        checkArticleIdIsExist(legacyId.toString())

        val emojiReactionArr = prepareEmojiReactionArr(emojiReactions)
        val mArrEmojiClickedDataItem = getUserEventEmojiArr()


        val childLayoutManager =
            LinearLayoutManager(emojiReactionsRecyclerView?.context, RecyclerView.HORIZONTAL, false)
        childLayoutManager.initialPrefetchItemCount = 10
        emojiReactionsRecyclerView?.apply {
            layoutManager = childLayoutManager
            adapter = EmojiReactionAdapter(
                emojiReactionsRecyclerView?.context!!,
                mArrEmojiClickedDataItem!!,
                emojiReactionArr
            ) { position, view, firstHeight, emojiItemCount, tvEmojiItemCount ->

                val emojiCode = emojiReactionArr[position].code

                if (!checkMaxEmojiReactionByUser(legacyId.toString()) || findEmojiCode(
                        legacyId.toString(),
                        emojiCode
                    ) == 1
                ) {
                    when (checkedEmojiIsClicked(legacyId.toString(), emojiCode)) {
                        1 -> {

                            articleDetailActivirtyPresenterImpl.downReactionNew(
                                legacyId,
                                emojiCode,
                                OnedioConstant.DOWN_REACTION + "$1",
                                view,
                                firstHeight,
                                emojiItemCount,
                                tvEmojiItemCount
                            )
                        }
                        0 -> {

                            articleDetailActivirtyPresenterImpl.upReactionNew(
                                legacyId,
                                emojiCode,
                                OnedioConstant.UP_REACTION + "$0",
                                view,
                                firstHeight,
                                emojiItemCount,
                                tvEmojiItemCount
                            )
                        }
                    }
                } else
                    Toast.makeText(
                        context,
                        "En fazla 3 emoji ile tepki verebilirsiniz..!",
                        Toast.LENGTH_LONG
                    ).show()

            }
            setRecycledViewPool(viewPool)
        }
    }

    private fun prepareEmojiReactionArr(emojiReactions: MutableList<ArticleReactionsModel>): MutableList<EmojiReactionModel> {
        val emojiReactionArr: MutableList<EmojiReactionModel> = mutableListOf()

        val sortedEmojiReactionArr =
            emojiReactions.sortedWith(compareBy { it.count }).reversed()

        sortedEmojiReactionArr.forEach { itemOfEmojiReaction ->
            emojiReactionArr.add(
                EmojiReactionModel(
                    itemOfEmojiReaction.count!!,
                    itemOfEmojiReaction.icons?.png!!,
                    itemOfEmojiReaction.id!!
                )
            )
        }

        return emojiReactionArr
    }

    private fun checkArticleIdIsExist(legacyId: String) {
        val sEmojiClickedData = StringSharedPrefs(
            OnedioCommon.getSharedPref()!!,
            OnedioConstant.EMOJI_REACTION_CLICKED_EMOJI_CODES,
            ""
        ).getValue<String>()


        if (sEmojiClickedData == "") {
            newMapOfEmojiReactionSharedPrefs = HashMap()
            newMapOfEmojiReactionSharedPrefs[legacyId] = mutableListOf()
        } else
            newMapOfEmojiReactionSharedPrefs =
                OnedioCommon.convertString2Map(sEmojiClickedData) as HashMap<String, MutableList<String>>


        newMapOfEmojiReactionSharedPrefs[legacyId]?.let {

        } ?: run {
            newMapOfEmojiReactionSharedPrefs[legacyId] = mutableListOf()
        }

        StringSharedPrefs(
            OnedioCommon.getSharedPref()!!,
            OnedioConstant.EMOJI_REACTION_CLICKED_EMOJI_CODES,
            ""
        ).setValue(OnedioCommon.convertMap2String(newMapOfEmojiReactionSharedPrefs))
    }

    private fun getUserEventEmojiArr(): MutableList<String>? {
        val sEmojiClickedData = StringSharedPrefs(
            OnedioCommon.getSharedPref()!!,
            OnedioConstant.EMOJI_REACTION_CLICKED_EMOJI_CODES,
            ""
        ).getValue<String>()
        val mEmojiClickedData = OnedioCommon.convertString2Map(sEmojiClickedData)

        return mEmojiClickedData[legacyId.toString()]
    }

    private fun checkMaxEmojiReactionByUser(legacyId: String): Boolean {
        val sEmojiClickedData = StringSharedPrefs(
            OnedioCommon.getSharedPref()!!,
            OnedioConstant.EMOJI_REACTION_CLICKED_EMOJI_CODES,
            ""
        ).getValue<String>()

        val mEmojiClickedData = OnedioCommon.convertString2Map(sEmojiClickedData)
        mEmojiClickedData[legacyId]?.remove("")
        if (mEmojiClickedData[legacyId]?.size!! < 3)
            return false
        return true

    }

    private fun findEmojiCode(legacyId: String, emojiCode: String): Int {
        val sEmojiClickedData = StringSharedPrefs(
            OnedioCommon.getSharedPref()!!,
            OnedioConstant.EMOJI_REACTION_CLICKED_EMOJI_CODES,
            ""
        ).getValue<String>()

        val mEmojiClicked = OnedioCommon.convertString2Map(sEmojiClickedData).toMutableMap()
        mEmojiClicked[legacyId]?.remove("")
        mEmojiClicked.forEach { (key, value) ->
            if (key == legacyId) {
                value.find { it == emojiCode }?.let {
                    return 1
                } ?: run {
                    return 0
                }
            }
        }

        return 0
    }

    private fun checkedEmojiIsClicked(legacyId: String, emojiCode: String): Int {
        return findEmojiCode(legacyId, emojiCode)
    }
}