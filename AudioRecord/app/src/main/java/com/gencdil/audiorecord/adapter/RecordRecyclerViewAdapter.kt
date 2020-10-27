package com.gencdil.audiorecord.adapter


import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gencdil.audiorecord.R
import com.gencdil.audiorecord.constant.RecordConstant
import com.gencdil.audiorecord.prefs.StringSharedPrefs

class RecordRecyclerViewAdapter(
    private val context: Context,
    private val sharedPrefs: SharedPreferences,
    private val countryList: MutableList<RecordModel>,
    private val listener: (Int, RecordModel, String, ImageView) -> Unit
) :
    RecyclerView.Adapter<RecordRecyclerViewAdapter.ModelViewHolder>() {

    class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val recordInfo: TextView = view.findViewById(R.id.recordInfo)
        private val recordTime: TextView = view.findViewById(R.id.recordTime)
        private val playRecord: ImageView = view.findViewById(R.id.playRecord)
        private val cardLayout: CardView = view.findViewById(R.id.cardLayout)

        @SuppressLint("SetTextI18n")
        fun bindItems(
            context: Context,
            sharedPrefs: SharedPreferences,
            item: RecordModel,
            pos: Int,
            listener: (Int, RecordModel, String, ImageView) -> Unit
        ) {
            cardLayout.setBackgroundColor(Color.parseColor("#000000"))

            recordInfo.text = StringSharedPrefs(
                sharedPrefs,
                RecordConstant.USER,
                ""
            ).getValue<String>() + item.date
            recordTime.text = item.time

            /*itemView.setOnClickListener{
                listener(pos, item)
            }*/

            itemView.setOnClickListener {
                listener(pos, item, "FORM", playRecord)
            }

            playRecord.setOnClickListener {
                listener(pos, item, "PLAY", playRecord)
            }

            recordInfo.typeface = Typeface.createFromAsset(context?.assets, "muli-regular.ttf")
            recordTime.typeface = Typeface.createFromAsset(context?.assets, "muli-regular.ttf")

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.record_item_card, parent, false)
        return ModelViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.bindItems(context, sharedPrefs, countryList.get(position), position, listener)
    }
}