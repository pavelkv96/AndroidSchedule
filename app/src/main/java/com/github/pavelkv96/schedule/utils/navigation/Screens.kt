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
package com.github.pavelkv96.schedule.utils.navigation

import androidx.fragment.app.Fragment
import com.github.pavelkv96.schedule.ui.flow.HomeFragment
import com.github.pavelkv96.schedule.ui.fragments.MainFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens(private val key: String, private val data: Any? = null) : SupportAppScreen() {

    override fun getScreenKey(): String {
        return key + "_" + key.hashCode()
    }

    override fun getFragment(): Fragment {
        return when (key) {
            main_flow -> HomeFragment.newInstance()
            main_fragment -> MainFragment.newInstance()
            else -> throw NullPointerException("Not found fragment with key screen: $key")
        }
    }

    companion object {

        const val main_flow = "home_flow"
        const val main_fragment = "main_fragment"
    }
}