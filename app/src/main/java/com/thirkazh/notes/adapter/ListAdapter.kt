package com.thirkazh.notes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.thirkazh.notes.model.ToDoData
import com.thirkazh.notes.databinding.RowLayoutBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var dataList = emptyList<ToDoData>()

    class MyViewHolder(private val binding : RowLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(toDoData: ToDoData) {
            binding.toDoData = toDoData
            binding.executePendingBindings()
        }

        companion object {
             fun from(parent: ViewGroup): MyViewHolder{
                 val layoutInflater = LayoutInflater.from(parent.context)
                 val binding = RowLayoutBinding.inflate(layoutInflater,parent,false)
                 return MyViewHolder(binding)
             }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder.from(parent)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int = dataList.size

    fun setData(toDoData: List<ToDoData>){
        val toDoDiffUtil = ToDoDiffUtil(dataList, toDoData)
        val toDoDiffUtilResult = DiffUtil.calculateDiff(toDoDiffUtil)
        this.dataList = toDoData
        toDoDiffUtilResult.dispatchUpdatesTo(this)
    }
}