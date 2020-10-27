package com.onedio.androidside.mvp.view.dashboard.homeFragment.messageActivity.conversation.holder

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.vanniktech.emoji.EmojiInformation
import com.vanniktech.emoji.EmojiTextView
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.chatModel.conversationModel.ChatData
import com.onedio.androidside.singleton.OnedioSingletonPattern

class HolderYou(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var time: TextView? = itemView.findViewById(R.id.chatTime) as TextView
    private var chatText: EmojiTextView? = itemView.findViewById(R.id.chatText) as EmojiTextView
    private var profilePhoto: ImageView? = itemView.findViewById(R.id.profilePhoto) as ImageView
    private var photoProgressMessage: ProgressBar =
        itemView.findViewById(R.id.photoProgressMessage) as ProgressBar

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

        if (OnedioSingletonPattern.instance.getConversationChatData().mImage.take(1) != "/" && OnedioSingletonPattern.instance.getConversationChatData().mImage != "")
            Picasso.get().load(OnedioSingletonPattern.instance.getConversationChatData().mImage)
                .into(profilePhoto, object : Callback {
                    override fun onSuccess() {
                        photoProgressMessage.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {

                    }
                })
        else
            photoProgressMessage.visibility = View.GONE

        time?.typeface = OnedioCommon.changeTypeFace4Activity(context)
        chatText?.typeface = OnedioCommon.changeTypeFace4Activity(context)



        /*profilePhoto?.setImageResource(R.drawable.empty_avatar)
        photoProgressMessage.visibility = View.GONE*/

        itemView.setOnClickListener {
            listener(pos)
        }
    }
}