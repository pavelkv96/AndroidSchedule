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
package com.github.pavelkv96.schedule.utils.base

import android.os.Bundle
import com.github.pavelkv96.schedule.R
import com.github.pavelkv96.schedule.utils.base.listeners.BackListener
import com.github.pavelkv96.schedule.utils.navigation.AppNavigator
import com.github.pavelkv96.schedule.utils.navigation.MyRouter
import com.github.pavelkv96.schedule.utils.navigation.Screens
import ru.terrakok.cicerone.Cicerone

abstract class BaseFlowFragment : BaseFragment(), BackListener {
    abstract fun start(): Screens
    abstract fun tag(): String
    override fun getLayout(): Int = R.layout.fragment_flow

    private lateinit var navigator: AppNavigator
    private lateinit var cicerone: Cicerone<MyRouter>
    internal lateinit var router: MyRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cicerone = Cicerone.create(MyRouter())
        router = cicerone.router

        if (savedInstanceState == null) {
            navigator = AppNavigator(activity, childFragmentManager, R.id.main_flow_container, tag())
            router.navigateTo(start())
        } else {
            navigator = savedInstanceState.getSerializable(tag()) as AppNavigator
            navigator.setFragmentManager(childFragmentManager)
        }
    }

    override fun onStart() {
        super.onStart()
        cicerone.navigatorHolder?.setNavigator(navigator)
    }

    override fun onStop() {
        cicerone.navigatorHolder?.removeNavigator()
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(tag(), navigator)
    }

    override fun onBackPressed(): Boolean {
        router.exit()
        return !navigator.stackList.isNullOrEmpty()
    }
}