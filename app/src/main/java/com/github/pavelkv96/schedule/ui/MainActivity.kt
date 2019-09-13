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
package com.github.pavelkv96.schedule.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.pavelkv96.schedule.App
import com.github.pavelkv96.schedule.R
import com.github.pavelkv96.schedule.data.storage.preferenses.PreferenceStore
import com.github.pavelkv96.schedule.utils.base.BaseFlowFragment
import com.github.pavelkv96.schedule.utils.navigation.AppNavigator
import com.github.pavelkv96.schedule.utils.navigation.MyRouter
import com.github.pavelkv96.schedule.utils.navigation.Screens
import ru.terrakok.cicerone.Cicerone
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private lateinit var navigator: AppNavigator
    private lateinit var cicerone: Cicerone<MyRouter>
    internal lateinit var router: MyRouter

    private val tag = MainActivity::class.java.simpleName

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(App.instance.setLocale(newBase, PreferenceStore.getLanguage()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cicerone = App.instance.cicerone
        router = cicerone.router
        if (savedInstanceState == null) {
            navigator = AppNavigator(this, supportFragmentManager, R.id.main_container, tag)
            router.navigateTo(Screens(Screens.main_flow))
        } else {
            navigator = savedInstanceState.getSerializable(tag) as AppNavigator
            navigator.setFragmentManager(supportFragmentManager)
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

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putSerializable(tag, navigator as Serializable)
    }

    private fun getCurrentFragment(): BaseFlowFragment? {
        return supportFragmentManager.findFragmentById(R.id.main_container) as? BaseFlowFragment
    }

    override fun onBackPressed() {
        val currentFragment = getCurrentFragment()
        if (!(currentFragment?.onBackPressed() ?: false)) {
            router.exit()
            if (navigator.stackList.isNullOrEmpty()) {
                router.finishChain()
            }
        }
    }
}