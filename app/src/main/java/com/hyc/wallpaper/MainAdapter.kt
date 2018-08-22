package com.hyc.wallpaper

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hyc.wallpaper.databinding.ItemMainBinding
import com.hyc.wallpaper.model.ItemDetail
import java.util.ArrayList

class MainAdapter(list: ArrayList<ItemDetail>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private var list: MutableList<ItemDetail> = list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var binding = DataBindingUtil.inflate<ItemMainBinding>(inflater, R.layout.item_main, parent, false)
        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var binding = DataBindingUtil.getBinding<ItemMainBinding>(holder.itemView)
        binding!!.itemDetail = list[position]
        binding.executePendingBindings()
    }


    fun addList(items:List<ItemDetail>){
        list.addAll(items)
    }
    class ViewHolder(item: View) : RecyclerView.ViewHolder(item)
}