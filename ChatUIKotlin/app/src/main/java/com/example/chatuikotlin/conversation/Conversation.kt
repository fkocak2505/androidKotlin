package com.example.chatuikotlin.conversation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatuikotlin.R
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection
import com.vanniktech.emoji.EmojiManager
import com.vanniktech.emoji.EmojiPopup
import com.vanniktech.emoji.ios.IosEmojiProvider
import kotlinx.android.synthetic.main.activity_conversation.*
import java.util.*
import androidx.recyclerview.widget.LinearSmoothScroller


class Conversation : AppCompatActivity(), SwipyRefreshLayout.OnRefreshListener {

    private lateinit var mAdapter: ConversationRecyclerView

    private lateinit var emojiPopup: EmojiPopup

    private lateinit var text: Array<String>
    private lateinit var time: Array<String>
    private lateinit var type: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        EmojiManager.install(IosEmojiProvider())

        setContentView(R.layout.activity_conversation)

        supportActionBar?.hide()
        userNameMessage.text = "Laura Owens"


        swipeRefresh.setOnRefreshListener(this)
        swipeRefresh.setColorSchemeColors(
                ContextCompat.getColor(applicationContext, R.color.colorAccent),
                ContextCompat.getColor(applicationContext, R.color.colorPrimaryDark2)
        )

        /*swipeRefresh.post {

            if (swipeRefresh != null)
                swipeRefresh.isRefreshing = true

            mAdapter = ConversationRecyclerView(this, setData2()) {
                Toast.makeText(applicationContext, setData2()[it].text, Toast.LENGTH_SHORT).show()
            }

        }*/

        /*try {
            val f = swipeRefresh.javaClass.getDeclaredField("mCircleView")
            f.setAccessible(true)
            val img = f.get(swipeRefresh) as ImageView
            img.setImageResource(R.drawable.ic_keyboard)
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
*/


        emojiButton.setOnClickListener { ignore -> emojiPopup.toggle() }

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = ConversationRecyclerView(this, setData()) {
            Toast.makeText(applicationContext, setData()[it].text, Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = mAdapter


        (recyclerView.layoutManager as LinearLayoutManager).setStackFromEnd(true);
        //recyclerView.smoothScrollToPosition(recyclerView.getAdapter()?.getItemCount()!! - 1)

        et_message.setOnTouchListener(View.OnTouchListener { v, event ->
            recyclerView.postDelayed(Runnable {
                //// Mesaj Yazma alanı açıldığında aşağıya focusluyor..
                recyclerView.smoothScrollToPosition(recyclerView.getAdapter()?.getItemCount()!! - 1)
            }, 200)
            false
        })

        bt_send.setOnClickListener(View.OnClickListener {
            if (et_message.text.toString() != "") {
                val data = ArrayList<ChatData>()
                data.add(
                        ChatData(
                                ((1 until 3).random()).toString(),
                                et_message.text.toString(),
                                "6:00pm"
                        )
                )
                mAdapter.addItem(data)
                recyclerView.smoothScrollToPosition(recyclerView.getAdapter()?.getItemCount()!! - 1)
                et_message.setText("")
            }
        })





        setUpEmojiPopup()

    }

    override fun onRefresh(direction: SwipyRefreshLayoutDirection?) {

        mAdapter = ConversationRecyclerView(this, setData2()) {
            Toast.makeText(applicationContext, setData2()[it].text, Toast.LENGTH_SHORT).show()
        }


        (recyclerView.layoutManager as LinearLayoutManager).setStackFromEnd(true)
        //recyclerView.smoothScrollToPosition(recyclerView.getAdapter()?.getItemCount()!! - 1)

        recyclerView.adapter = mAdapter
        swipeRefresh.isRefreshing = false
    }


    fun setData2(): MutableList<ChatData> {
        val data = ArrayList<ChatData>()

        text = arrayOf(
                "28 Ağustos",
                "Hi, Julia! How are you?",
                "Hi, Joe, looks great! :) ",
                "I'm fine. Wanna go out somewhere?",
                "Yes! Coffe maybe?",
                "Great idea! You can come 9:00 pm? :)))",
                "Ok!",
                "Ow my good, this Kit is totally awesome",
                "29 Ağustos",
                "Can you provide other kit?",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "Can you provide other kit?",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "Can you provide other kit?",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "Can you provide other kit?",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "Can you provide other kit?",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "Can you provide other kit?",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "Can you provide other kit?",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "IIII don't have much time, :`(",
                "I doooon't have much time, :`("
        )
        time = arrayOf(
                "",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 11 dk önce",
                "yaklaşık 13 dk önce",
                "yaklaşık 1 dk önce"
        )
        type = arrayOf("0", "2", "1", "1", "2", "1", "2", "2", "0", "1", "1", "2", "2", "1", "1", "1", "2", "2", "1", "1", "1", "2", "2", "1", "1", "1", "2", "2", "1", "1", "1", "2", "2", "1", "1", "1", "2", "2", "1", "1", "1", "2", "2", "1", "2", "1")

        for (i in text.indices)
            data.add(ChatData(type[i], text[i], time[i]))

        return data
    }


    fun setData(): MutableList<ChatData> {
        val data = ArrayList<ChatData>()

        text = arrayOf(
                "28 Ağustos",
                "Hi, Julia! How are you?",
                "Hi, Joe, looks great! :) ",
                "I'm fine. Wanna go out somewhere?",
                "Yes! Coffe maybe?",
                "Great idea! You can come 9:00 pm? :)))",
                "Ok!",
                "Ow my good, this Kit is totally awesome",
                "29 Ağustos",
                "Can you provide other kit?",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "Can you provide other kit?",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "Can you provide other kit?",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "Can you provide other kit?",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "Can you provide other kit?",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "Can you provide other kit?",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "Can you provide other kit?",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "I don't have much time, :`(",
                "I don't have much time, :`("
        )
        time = arrayOf(
                "",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce",
                "yaklaşık 1 dk önce"
        )
        type = arrayOf("0", "2", "1", "1", "2", "1", "2", "2", "0", "1", "1", "2", "2", "1", "1", "1", "2", "2", "1", "1", "1", "2", "2", "1", "1", "1", "2", "2", "1", "1", "1", "2", "2", "1", "1", "1", "2", "2", "1", "1", "1", "2", "2", "1")

        for (i in text.indices)
            data.add(ChatData(type[i], text[i], time[i]))

        return data
    }

    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_userphoto, menu)
        return true
    }*/

    private fun setUpEmojiPopup() {
        emojiPopup = EmojiPopup.Builder.fromRootView(rootConstraintLayout)
                .setOnEmojiBackspaceClickListener { ignore -> }
                .setOnEmojiClickListener { ignore, ignore2 -> }
                .setOnEmojiPopupShownListener { emojiButton.setImageResource(R.drawable.ic_keyboard) }
                .setOnSoftKeyboardOpenListener { ignore -> }
                .setOnEmojiPopupDismissListener { emojiButton.setImageResource(R.drawable.emoji_ios_category_smileysandpeople) }
                .setOnSoftKeyboardCloseListener { }
                .setKeyboardAnimationStyle(R.style.emoji_fade_animation_style)
                .setPageTransformer(PageTransformer())
                .build(et_message)
    }
}