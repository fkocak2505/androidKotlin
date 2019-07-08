package tr.com.fkocak.kotlinretrofit.mvp.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import tr.com.fkocak.kotlinretrofit.R
import tr.com.fkocak.kotlinretrofit.mvp.model.responseModel.Hero

class HeroesAdapter(val heroesList: MutableList<Hero>, val listener: (Int) -> Unit) :
    RecyclerView.Adapter<HeroesAdapter.ModelViewHolder>() {

    class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val name : TextView = view.findViewById(R.id.name)
        val realName : TextView = view.findViewById(R.id.realName)

        fun bindItems(item: Hero, pos: Int, listener: (Int) -> Unit){
            name.setText(item.name)
            realName.setText(item.realname)

            itemView.setOnClickListener{
                listener(pos)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return ModelViewHolder(view)
    }

    override fun getItemCount(): Int {
        return heroesList.size
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.bindItems(heroesList.get(position), position, listener)
    }


}