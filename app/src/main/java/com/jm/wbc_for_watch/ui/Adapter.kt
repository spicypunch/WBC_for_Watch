package com.jm.wbc_for_watch.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jm.wbc_for_watch.data.BusInfoEntity
import com.jm.wbc_for_watch.databinding.ItemBookmarkBinding

class Adapter() : ListAdapter<BusInfoEntity, Adapter.MyViewHolder>(diffUtil){

    class MyViewHolder(private val binding: ItemBookmarkBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BusInfoEntity) {
            Log.e("item", item.toString())
            binding.data = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemBookmarkBinding = ItemBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<BusInfoEntity>() {

            override fun areItemsTheSame(oldItem: BusInfoEntity, newItem: BusInfoEntity): Boolean {
                return (oldItem.busNum == newItem.busNum || oldItem.predictTime1 == newItem.predictTime1 || oldItem.predictTime2 == newItem.predictTime2)
            }

            override fun areContentsTheSame(oldItem: BusInfoEntity, newItem: BusInfoEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}