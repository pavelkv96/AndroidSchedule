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
package com.github.pavelkv96.schedule.data.storage.preferenses

import android.content.SharedPreferences
import com.github.pavelkv96.schedule.App
import com.github.pavelkv96.schedule.utils.constants.Language
import com.github.pavelkv96.schedule.utils.constants.SharedPref

@Suppress("unused")
object PreferenceStore {

    private var preference: SharedPreferences = App.instance.getPreference()

    fun getDays(defValue: Int = 7): Int {
        return preference.getInt(SharedPref.PREF_NUMBER_OF_DAYS, defValue)
    }

    fun getLastSchedule(defValue: Boolean = false): Boolean {
        return preference.getBoolean(SharedPref.PREF_LAST_SCHEDULE, defValue)
    }

    fun getLanguage(defValue: Language = Language.en): String {
        return preference.getString(SharedPref.PREF_LANGUAGE, defValue.name) ?: defValue.name
    }
}