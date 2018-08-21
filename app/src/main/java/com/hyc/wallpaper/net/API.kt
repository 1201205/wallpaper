package com.hyc.wallpaper.net

import com.hyc.wallpaper.model.BaseModel
import com.hyc.wallpaper.model.ItemDetail
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface API {
    @GET("/api/hp/idlist/{id}")
    fun getIDList(@Path("id") id:String):Observable<BaseModel<List<String>>>

    @GET("/api/hp/detail/{id}")
    fun getItemDetail(@Path("id") id: String):Observable<BaseModel<ItemDetail>>
}