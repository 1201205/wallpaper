package com.hyc.wallpaper

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.databinding.ObservableInt

class MainViewModel(app:Application): AndroidViewModel(app) {
    var count:MutableLiveData<String> = MutableLiveData()
    var adapter:MutableLiveData<MainAdapter> = MutableLiveData()
    fun setItemCount(itemCount:Int){
        count.postValue(itemCount.toString())
    }
}