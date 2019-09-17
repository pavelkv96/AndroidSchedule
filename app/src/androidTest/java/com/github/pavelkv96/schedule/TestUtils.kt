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

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.test.platform.app.InstrumentationRegistry
import com.github.pavelkv96.schedule.utils.base.BaseFlowFragment

fun rotateScreen(activity: AppCompatActivity, isLandscape: Boolean) {
    InstrumentationRegistry.getInstrumentation().runOnMainSync {
        activity.requestedOrientation =
            if (isLandscape) ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE else ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
    InstrumentationRegistry.getInstrumentation().waitForIdleSync()
    Thread.sleep(500)
}

@Suppress("unchecked_cast")
fun <T : BaseFlowFragment> getCurrentFlowFragment(activity: AppCompatActivity): T? {
    return activity.supportFragmentManager.findFragmentById(R.id.main_container) as? T
}

@Suppress("unchecked_cast")
fun <T : Fragment> getCurrentChildFlowFragment(fragment: BaseFlowFragment): T? {
    return fragment.childFragmentManager.findFragmentById(R.id.main_flow_container) as? T
}