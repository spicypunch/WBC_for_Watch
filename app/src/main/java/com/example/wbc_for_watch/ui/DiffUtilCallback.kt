package com.example.wbc_for_watch.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.wbc_for_watch.data.BusInfoEntity

class DiffUtilCallback(
    private val oldList: MutableList<BusInfoEntity>,
    private val newList: MutableList<BusInfoEntity>): DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].busNum == newList[newItemPosition].busNum

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

}