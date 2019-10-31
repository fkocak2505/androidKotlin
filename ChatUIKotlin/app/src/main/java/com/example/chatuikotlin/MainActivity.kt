package com.example.chatuikotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatuikotlin.conversation.Conversation
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Long
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity(), ViewHolder.ClickListener {

    private lateinit var mAdapter: ChatAdapter
    val name = arrayOf(
        "Laura Owens",
        "Angela Price",
        "Donald Turner",
        "Kelly",
        "Julia Harris",
        "Laura Owens",
        "Angela Price",
        "Donald Turner",
        "Kelly",
        "Julia Harris"
    )
    val lastchat = arrayOf(
        "Hi Laura Owens",
        "Hi there how are you",
        "Can we meet?",
        "Ow this awesome",
        "How are you?",
        "Ow this awesome",
        "How are you?",
        "Ow this awesome",
        "How are you?",
        "How are you?"
    )
    val img = intArrayOf(
        R.drawable.userpic,
        R.drawable.userpic,
        R.drawable.userpic,
        R.drawable.userpic,
        R.drawable.userpic,
        R.drawable.userpic,
        R.drawable.userpic,
        R.drawable.userpic,
        R.drawable.userpic,
        R.drawable.userpic
    )
    val online = booleanArrayOf(true, false, true, false, true, true, true, false, false, true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        mAdapter = ChatAdapter(applicationContext, setData(), this)
        recyclerView.adapter = mAdapter

        tv_selection.setOnClickListener(){
            var newChatList = mAdapter.arrayList.filter { s -> !s.isDelete }
            mAdapter = ChatAdapter(applicationContext, newChatList, this)
            recyclerView.adapter = mAdapter
            tv_selection.visibility = View.GONE
        }

    }

    fun setData(): List<Chat> {
        val data = ArrayList<Chat>()


        for (i in 0..9)
            data.add(Chat(name[i], lastchat[i], "5:04pm", img[i], online[i], false))
        return data
    }

    override fun onItemClicked(position: Int) {
        /// Sıngleton userName
        if (mAdapter.selectedItemCount > 0)
            toggleSelection(position)
        else
            startActivity(Intent(applicationContext, Conversation::class.java))


    }

    override fun onItemLongClicked(position: Int): Boolean {
        toggleSelection(position)
        return true
    }

    @SuppressLint("SetTextI18n")
    private fun toggleSelection(position: Int) {
        mAdapter.toggleSelection(position)
        if (mAdapter.selectedItemCount > 0) {
            tv_selection.visibility = View.VISIBLE
        } else
            tv_selection.visibility = View.GONE


        runOnUiThread(Runnable {
            tv_selection.text = "Delete (" + mAdapter.selectedItemCount + ")"
        })



    }
}
