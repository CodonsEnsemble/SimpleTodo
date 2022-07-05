package com.example.simpletodo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskItemAdapter(val  listOfItems : List<String>, val longClickListerner: OnLongClickListerner): RecyclerView.Adapter<TaskItemAdapter.ViewHolder>() {

    interface OnLongClickListerner{
        fun OnItemLongClicked(position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: TaskItemAdapter.ViewHolder, position: Int) {

        val item = listOfItems[position]
        holder.textview.text = item
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textview: TextView

        init {
            textview = itemView.findViewById(android.R.id.text1)
            itemView.setOnLongClickListener{
                Log.i("Adapter", "User cliqued on button")
                true

            }
        }
    }

}