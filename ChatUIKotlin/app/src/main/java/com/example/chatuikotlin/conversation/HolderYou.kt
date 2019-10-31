package com.example.chatuikotlin.conversation

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatuikotlin.R
import com.vanniktech.emoji.EmojiInformation
import com.vanniktech.emoji.EmojiTextView

class HolderYou(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var time: TextView? = itemView.findViewById(R.id.time) as TextView
    private var chatText: EmojiTextView? = itemView.findViewById(R.id.chatText) as EmojiTextView

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
            chatText?.setBackgroundResource(R.drawable.bg_chat_you)
        }

        time?.text = item.time
        chatText?.setEmojiSizeRes(res, false)
        chatText?.text = item.text

        itemView.setOnClickListener {
            listener(pos)
        }
    }

    fun getTime(): TextView? {
        return time
    }

    fun setTime(time: TextView) {
        this.time = time
    }

    fun getChatText(): EmojiTextView? {
        return chatText
    }

    fun setChatText(chatText: EmojiTextView) {
        this.chatText = chatText
    }

}