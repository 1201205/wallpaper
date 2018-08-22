package com.hyc.wallpaper

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.util.Log
import com.hyc.wallpaper.model.AppDatabase
import com.hyc.wallpaper.model.BaseModel
import com.hyc.wallpaper.model.ItemDetail
import com.hyc.wallpaper.net.RequestClient
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiConsumer
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
import java.util.concurrent.Callable

class MainViewModel(app: Application) : AndroidViewModel(app) {
    var count: MutableLiveData<String> = MutableLiveData()
    var adapter: MutableLiveData<MainAdapter> = MutableLiveData()
    var isLoading: Boolean = false
    var mCurrentId: String = "null"
    fun setItemCount(itemCount: Int) {
        count.postValue(itemCount.toString())
    }

    fun firstGetItems() {
        if (adapter.value == null) {
            getItems()
        }
    }

    fun getItems() {
        if (isLoading) {
            return
        }
        isLoading = true
        RequestClient.instance.getIDList(mCurrentId).flatMap {
            Observable.fromIterable(it.data)
        }.flatMap { t ->
            var flowable = AppDatabase.instance.itemDetailDao().select(t)
            if (flowable.isEmpty.blockingGet()) {
                Observable.just(RequestClient.instance.getItemDetail(t).blockingFirst().data)
            } else {
                flowable.toObservable()
            }
        }.collect(Callable<ArrayList<ItemDetail>> { ArrayList() }, BiConsumer<ArrayList<ItemDetail>, ItemDetail> { t1, t2 ->
            AppDatabase.instance.itemDetailDao().insert(t2)
            t1?.add(t2)
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { t ->
                    isLoading = false
                    if (adapter.value == null) {
                        adapter.postValue(MainAdapter(t))
                    } else {
                        var mainAdapter = adapter.value
                        mainAdapter?.apply {
                            var size = this.itemCount
                            addList(t)
                            notifyItemInserted(size)
                        }
                    }
                    mCurrentId = t.last().hpcontent_id!!
                }
    }
}