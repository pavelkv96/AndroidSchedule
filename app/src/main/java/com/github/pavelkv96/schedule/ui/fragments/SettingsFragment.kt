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
package com.github.pavelkv96.schedule.ui.fragments

import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.github.pavelkv96.schedule.App
import com.github.pavelkv96.schedule.R
import com.github.pavelkv96.schedule.data.storage.preferenses.PreferenceStore
import com.github.pavelkv96.schedule.ui.custom.NumberPreference
import com.github.pavelkv96.schedule.ui.custom.NumberPreferenceDialogFragment
import com.github.pavelkv96.schedule.utils.constants.SharedPref

class SettingsFragment : PreferenceFragmentCompat(), OnSharedPreferenceChangeListener {

    private lateinit var pref_language: ListPreference
    private lateinit var pref_number_of_days: NumberPreference
    private lateinit var pref_last_schedule: SwitchPreferenceCompat

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
        super.onPause()
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference, rootKey)
        pref_language = findPreference(SharedPref.PREF_LANGUAGE) as ListPreference
        pref_number_of_days = findPreference(SharedPref.PREF_NUMBER_OF_DAYS) as NumberPreference
        pref_last_schedule = findPreference(SharedPref.PREF_LAST_SCHEDULE) as SwitchPreferenceCompat

        pref_language.summary = pref_language.entry

        val day = pref_number_of_days.getTime()
        val days = resources.getQuantityString(R.plurals.pref_title_days, day, day)
        pref_number_of_days.summary = days
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            SharedPref.PREF_LANGUAGE -> {
                pref_language.summary = pref_language.entry
                App.instance.setLocale(context, PreferenceStore.getLanguage())
                activity?.recreate()
            }
            SharedPref.PREF_NUMBER_OF_DAYS -> {
                val day = PreferenceStore.getDays()
                val days = resources.getQuantityString(R.plurals.pref_title_days, day, day)
                pref_number_of_days.summary = days
            }
        }
    }

    override fun onDisplayPreferenceDialog(preference: Preference) {
        if (preference is NumberPreference) {
            NumberPreferenceDialogFragment.newInstance(preference.key).also {
                it.setTargetFragment(this, 0)
            }.show(fragmentManager, "androidx.preference.PreferenceFragment.DIALOG")
        } else {
            super.onDisplayPreferenceDialog(preference)
        }
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }
}