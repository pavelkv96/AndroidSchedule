/*
  * Copyright (C) 2019 The Android Open Source Project
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  *      http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
 */
package com.github.pavelkv96.schedule.data.network

import com.github.pavelkv96.schedule.App
import com.github.pavelkv96.schedule.data.network.api.ScheduleApi
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object Network {

    private const val baseUrl = "http://api.grsu.by/1.x/app1/"
    private const val timeOut: Long = 30
    private const val cacheSize: Long = 10 * 1024 * 1024

    fun create(): ScheduleApi {
        val cacheFile = File(App.instance.cacheDir, "cachedSchedule")
        val cache = Cache(cacheFile, cacheSize)

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(timeOut, TimeUnit.SECONDS)
            .writeTimeout(timeOut, TimeUnit.SECONDS)
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .addInterceptor(OFFLINE_INTERCEPTOR)
            .cache(cache)
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()

        return retrofit.create(ScheduleApi::class.java)
    }

    private val OFFLINE_INTERCEPTOR = Interceptor { chain ->
        var request = chain.request()
        val url = request.url()
        val maxStale = 60 * 60 * 24 * 21
        request = request.newBuilder().url(url)
            .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
            .build()
        chain.proceed(request)
    }
}