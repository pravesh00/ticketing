package com.example.ticketing.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ticketing.databinding.EventLayoutBinding
import com.example.ticketing.databinding.OrderDetailsItemBinding
import com.example.ticketing.model.Order
import com.example.ticketing.model.OrderDetails

class OrderAdapter(private val orders:ArrayList<OrderDetails>):RecyclerView.Adapter<OrderAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: OrderDetailsItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = OrderDetailsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var order = orders[position]
        holder.binding.txtName.text = order.name
        holder.binding.txtType.text = order.typeEvent
        holder.binding.txtStatus.text= order.status
        Glide.with(holder.binding.root.context).load(order.photo).centerCrop().into(holder.binding.imgPhoto)
        if(order.status=="Confirmed"){
            holder.binding.btnCancel.isVisible = true
            holder.binding.txtStatus.setTextColor(Color.parseColor("#4C9A3A"))
        }else{
            holder.binding.btnCancel.isVisible = false
            holder.binding.txtStatus.setTextColor(Color.parseColor("Red"))
        }
        holder.binding.txtseats.text = order.orderSeats
    }

    override fun getItemCount(): Int {
       return orders.size
    }
}