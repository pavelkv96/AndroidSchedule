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
import com.github.pavelkv96.schedule.utils.navigation.MyRouter
import ru.terrakok.cicerone.Cicerone

class App : Application() {
    lateinit var cicerone: Cicerone<MyRouter>
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        cicerone = Cicerone.create(MyRouter())
    }

    companion object {

        lateinit var instance: App
            private set
    }
}