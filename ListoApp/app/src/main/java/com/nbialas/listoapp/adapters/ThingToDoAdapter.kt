package com.nbialas.listoapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nbialas.listoapp.R
import com.nbialas.listoapp.models.ThingToDo
import kotlinx.android.synthetic.main.item_thing.view.*


class ThingToDoDataAdapter : RecyclerView.Adapter<ThingDataHolder>() {

    private var data: List<ThingToDo>? = null
    var onClickAction: (thing: ThingToDo) -> Unit = {}
    var removeItem: (thing: ThingToDo) -> Unit = {}

    fun setData(items: List<ThingToDo>?) {
        this.data = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThingDataHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_thing, parent, false)
        return ThingDataHolder(view)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: ThingDataHolder, position: Int) {
        val thingToDo = data?.get(position)
        thingToDo?.let { thingItem ->
            holder.itemView.apply {
                thingItemName.text = thingItem.name
                thingItemFrame.apply {
                    setCardBackgroundColor(thingItem.color)
                    setOnClickListener { onClickAction(thingItem) }
                }
                thingRemoveButton.setOnClickListener { removeItem(thingItem) }
            }
        }
    }
}

class ThingDataHolder(v: View) : RecyclerView.ViewHolder(v)