package com.onedio.androidside.mvp.view.dashboard.homeFragment.messageActivity.conversation.holder

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vanniktech.emoji.EmojiInformation
import com.vanniktech.emoji.EmojiTextView
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.chatModel.conversationModel.ChatData

class HolderMe(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var time: TextView? = itemView.findViewById(R.id.timeMe) as TextView
    private var chatText: EmojiTextView? = itemView.findViewById(R.id.chatTextMe) as EmojiTextView


    fun bindItems(
        context: Context,
        item: ChatData,
        emojiInformation: EmojiInformation,
        pos: Int,
        listener: (Int) -> Unit
    ) {
        val res: Int

        if (emojiInformation.isOnlyEmojis && emojiInformation.emojis.size == 1) {
            res = R.dimen.emoji_size_single_emoji
            chatText?.setBackgroundResource(R.drawable.bg_chat_emoji)
        } else if (emojiInformation.isOnlyEmojis && emojiInformation.emojis.size > 1) {
            res = R.dimen.emoji_size_only_emojis
            chatText?.setBackgroundResource(R.drawable.bg_chat_emoji)
        } else {
            res = R.dimen.emoji_size_default
            chatText?.setBackgroundResource(R.drawable.bg_chat_me)
        }

        time?.text = item.time
        chatText?.setEmojiSizeRes(res, false)
        chatText?.text = item.text

        time?.typeface = OnedioCommon.changeTypeFace4Activity(context)
        chatText?.typeface = OnedioCommon.changeTypeFace4Activity(context)

        itemView.setOnClickListener {
            listener(pos)
        }
    }
}