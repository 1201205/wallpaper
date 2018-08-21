package com.hyc.wallpaper

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.hyc.wallpaper.databinding.ActivityMainBinding
import com.hyc.wallpaper.model.ItemDetail
import com.hyc.wallpaper.net.RequestClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    lateinit var tt:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        var viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        RequestClient.instance.getIDList("null").subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe {
            Log.e("hyc--oo", it.data.toString() + "---" + it.res + "_-----" + Thread.currentThread().name)
            viewModel.setItemCount(3871)
        }
        RequestClient.instance.getItemDetail("2175").subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe {
            Log.e("hyc--oo", it.data.toString())
            var list:java.util.ArrayList<ItemDetail> = java.util.ArrayList<ItemDetail>()
//            var list:List<ItemDetail> =ArrayList<ItemDetail>()
            list.add(it.data)
            list.add(it.data)
            list.add(it.data)
            list.add(it.data)
            list.add(it.data)
            list.add(it.data)
            list.add(it.data)
            list.add(it.data)
            viewModel.adapter.postValue(MainAdapter(list))
//            var layout=LinearLayoutManager(tt.context)
//            layout.orientation=LinearLayoutManager.VERTICAL
//            tt.layoutManager=layout
//            tt.adapter=MainAdapter(list)
        }
        val observer: Observer<String> = Observer<String> {
            Log.e("hyc-ii", "----$it")
        }
//        viewModel.count.observe(this,observer)
    }
}
