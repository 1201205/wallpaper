package com.hyc.wallpaper.net

import android.text.TextUtils
import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class RequestClient private constructor() {

    companion object {
        val instance: API by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            val tag = "request"
            var interceptor = Interceptor { chain ->
                val request = chain.request()
                Log.v(tag, "request:" + request.toString())
                val t1 = System.nanoTime()
                val response = chain.proceed(request)
                val t2 = System.nanoTime()
                Log.v(tag, String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                        response.request().url(), (t2 - t1) / 1e6, response.headers()))
                var cacheControl = request.cacheControl().toString()
                if (TextUtils.isEmpty(cacheControl)) {
                    cacheControl = "public, max-age=60"
                }
                response.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build()
            }
            var client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            Retrofit.Builder().baseUrl("http://v3.wufazhuce.com:8000").addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).client(client!!).build().create(API::class.java)
        }
    }
}