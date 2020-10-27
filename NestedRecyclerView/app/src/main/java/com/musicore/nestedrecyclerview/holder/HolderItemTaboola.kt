package com.musicore.nestedrecyclerview.holder

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.musicore.nestedrecyclerview.R
import com.musicore.nestedrecyclerview.model.ArticleDetailAdapterModel
import com.taboola.android.PublisherInfo
import com.taboola.android.Taboola
import com.taboola.android.TaboolaWidget
import com.taboola.android.utils.SdkDetailsHelper
import java.util.regex.Pattern


class HolderItemTaboola(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var taboolaWidget: TaboolaWidget? =
        itemView.findViewById(R.id.taboolaWidget) as TaboolaWidget

    fun bindItems(
        context: Context,
        item: ArticleDetailAdapterModel,
        listener: (Int, ArticleDetailAdapterModel, String) -> Unit,
        pos: Int
    ) {

        val publisherInfo = PublisherInfo("onedio-app-android")
        Taboola.init(publisherInfo)

        var title = ""
        var legacyId: Long = 0
        item.data.data?.let {
            it.title?.let {
                title = it
            } ?: run {
             title = ""
            }

            it.legacyId?.let {
                legacyId = it.toLong()
            }?: run{
                legacyId = 0
            }
        }

        if(title != "" && legacyId != 0.toLong()){
            val pageUrl = convertPageUrl(title, legacyId.toString())

            taboolaWidget
                ?.setPublisher("onedio-app-android")
                ?.setPageType("article")
                ?.setPageUrl(pageUrl)
                ?.setPlacement("Below Article Thumbnails")
                ?.setMode("alternating-thumbnails-a")
                ?.setTargetType("mix")
                ?.setInterceptScroll(true)

            /*val extraProperties: HashMap<String, String> = HashMap()
            extraProperties["enableHorizontalScroll"] = "true"
            extraProperties["useOnlineTemplate"] = "true"
            taboolaWidget!!.setExtraProperties(extraProperties)*/

            taboolaWidget!!.fetchContent()
        }
    }

    private fun convertPageUrl(articleTitle: String, legacyId: String): String {
        var newUrl = articleTitle

        newUrl = convertString4TaboolaUrl(newUrl)
        return "https://onedio.com/$newUrl-$legacyId"

    }

    fun convertString4TaboolaUrl(title: String): String {
        var articleTitle = title
        /*articleTitle = articleTitle.replace(" ", "-")*/

        if (articleTitle.first().toString() == "'")
            articleTitle = articleTitle.removePrefix("'")
        if (articleTitle.last().toString() == "'")
            articleTitle = articleTitle.removeSuffix("'")

        articleTitle = articleTitle.replace("İ", "i")
        articleTitle = articleTitle.toLowerCase()


        val builder = StringBuilder(articleTitle)
        val regex = "[;/:*?\"<>|&]"
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(builder.toString())
        articleTitle = matcher.replaceAll("")

        articleTitle = articleTitle.replace("!", "")
        articleTitle = articleTitle.replace(".", "")
        articleTitle = articleTitle.replace(",", "")

        articleTitle = articleTitle.replace(" '", "-")
        articleTitle = articleTitle.replace("' ", "-")
        articleTitle = articleTitle.replace(" ’", "-")
        articleTitle = articleTitle.replace("’ ", "-")
        articleTitle = articleTitle.replace("'", "-")
        articleTitle = articleTitle.replace("’", "-")
        articleTitle = articleTitle.replace(" ", "-")

        val turkishChars = charArrayOf(
            0x131.toChar(),
            0x130.toChar(),
            0xFC.toChar(),
            0xDC.toChar(),
            0xF6.toChar(),
            0xD6.toChar(),
            0x15F.toChar(),
            0x15E.toChar(),
            0xE7.toChar(),
            0xC7.toChar(),
            0x11F.toChar(),
            0x11E.toChar()
        )
        val englishChars =
            charArrayOf('i', 'I', 'u', 'U', 'o', 'O', 's', 'S', 'c', 'C', 'g', 'G')
        for (i in turkishChars.indices) {
            articleTitle = articleTitle.replace(
                String(charArrayOf(turkishChars[i])),
                String(charArrayOf(englishChars[i]))
            )
        }

        return articleTitle

    }

}