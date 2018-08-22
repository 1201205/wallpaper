package com.hyc.wallpaper

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.hyc.wallpaper.databinding.ActivityMainBinding
import com.hyc.wallpaper.model.BaseModel
import com.hyc.wallpaper.model.ItemDetail
import com.hyc.wallpaper.net.RequestClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiConsumer
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
import java.util.concurrent.Callable
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    lateinit var rvList: RecyclerView
    lateinit var mManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        rvList = findViewById(R.id.tt)
        viewModel.firstGetItems()
        mManager = LinearLayoutManager(this)
        mManager.orientation = LinearLayoutManager.VERTICAL
        rvList.layoutManager = mManager
        rvList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (mManager.findLastVisibleItemPosition() > rvList.adapter.itemCount - 3) {
                    viewModel.getItems()
                }
            }
        })
        viewModel.adapter.observe(this, Observer<MainAdapter> { t -> Log.e("hyc--ooo", "----" + t!!.itemCount) })
//        viewModel.count.observe(this,observer)
    }

    override fun onStop() {
        super.onStop()
//        Debug.stopMethodTracing()
    }
}
