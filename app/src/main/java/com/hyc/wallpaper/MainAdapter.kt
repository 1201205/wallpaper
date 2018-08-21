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

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder> {
     private var list:List<ItemDetail>
     constructor(list:ArrayList<ItemDetail>){
        this.list=list
        Log.e("hyc--oo","create")
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater=LayoutInflater.from(parent.context)
        var binding=DataBindingUtil.inflate<ItemMainBinding>(inflater,R.layout.item_main,parent,false)
        Log.e("hyc--oo","onCreateViewHolder")

        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        Log.e("hyc--oo","getItemCount---"+list.size)
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.e("hyc--oo","onBindViewHolder")
        var binding=DataBindingUtil.getBinding<ItemMainBinding>(holder.itemView)
        binding!!.itemDetail=list[position]
        binding.executePendingBindings()
    }

    class ViewHolder(item:View): RecyclerView.ViewHolder(item) {

    }
}