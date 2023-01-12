package com.example.ticketing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ticketing.R
import com.example.ticketing.databinding.EventLayoutBinding
import com.example.ticketing.model.Event

class EventAdapter(var list:List<Event>):RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: EventLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = EventLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var event= list[position]
        holder.binding.txtName.setText(event.name)
        holder.binding.txtType.setText(event.typeEvent)
        Glide.with(holder.itemView.context).load(event.photo)
            .placeholder(R.drawable.button_outline)
            .centerCrop()
            .into(holder.binding.imgPhoto)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}