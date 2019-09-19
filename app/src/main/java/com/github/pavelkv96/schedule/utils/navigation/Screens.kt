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
import com.github.pavelkv96.schedule.ui.fragments.*
import com.github.pavelkv96.schedule.ui.fragments.about.AboutFragment
import com.github.pavelkv96.schedule.ui.fragments.teacher.TeachersListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens(private val key: String, private val data: Any? = null) : SupportAppScreen() {

    override fun getScreenKey(): String {
        return key + "_" + key.hashCode()
    }

    override fun getFragment(): Fragment {
        return when (key) {
            main_flow -> HomeFragment.newInstance()
            about_fragment -> AboutFragment.newInstance()
            departments_fragment -> DepartmentsListFragment.newInstance()
            bookmarks_fragment -> BookmarksFragment.newInstance()
            settings_fragment -> SettingsFragment.newInstance()
            student_fragment -> StudentFragment.newInstance()
            teachers_fragment -> TeachersListFragment.newInstance()
            else -> throw NullPointerException("Not found fragment with key screen: $key")
        }
    }

    companion object {

        const val main_flow = "home_flow"
        const val about_fragment = "about_fragment"
        const val departments_fragment = "departments_fragment"
        const val bookmarks_fragment = "bookmarks_fragment"
        const val settings_fragment = "settings_fragment"
        const val student_fragment = "student_fragment"
        const val teachers_fragment = "teachers_fragment"
    }
}