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
package com.github.pavelkv96.schedule

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Build
import androidx.preference.PreferenceManager
import com.github.pavelkv96.schedule.data.network.api.ScheduleApi
import com.github.pavelkv96.schedule.data.network.Network
import com.github.pavelkv96.schedule.utils.navigation.MyRouter
import ru.terrakok.cicerone.Cicerone
import java.util.*

class App : Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    lateinit var cicerone: Cicerone<MyRouter>
        private set

    private lateinit var preference: SharedPreferences
    private lateinit var api: ScheduleApi

    override fun onCreate() {
        super.onCreate()
        instance = this
        preference = PreferenceManager.getDefaultSharedPreferences(this)
        cicerone = Cicerone.create(MyRouter())

        api = Network.create()
    }

    @Suppress("deprecation")
    fun setLocale(context: Context?, language: String): Context? {
        if (context == null) {
            return context
        }

        val locale = Locale(language)
        Locale.setDefault(locale)

        val res = context.resources

        val conf = Configuration(res.configuration)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            conf.setLocale(locale)
            context.createConfigurationContext(conf)
        } else {
            conf.locale = locale
            res.updateConfiguration(conf, res.displayMetrics)
            context
        }
    }

    fun getPreference(): SharedPreferences {
        return preference
    }

    fun getNetwork() = api
}