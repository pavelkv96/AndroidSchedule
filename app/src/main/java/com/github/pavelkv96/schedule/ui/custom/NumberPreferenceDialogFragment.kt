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
package com.github.pavelkv96.schedule.ui.custom

import android.os.Bundle
import android.view.View
import android.widget.NumberPicker
import androidx.preference.PreferenceDialogFragmentCompat
import com.github.pavelkv96.schedule.R
import com.github.pavelkv96.schedule.data.storage.preferenses.PreferenceStore

class NumberPreferenceDialogFragment : PreferenceDialogFragmentCompat() {

    companion object {
        private const val SAVE_STATE_MIN_DAY = "NumberPreferenceDialogFragment.min_day"
        private const val SAVE_STATE_MAX_DAY = "NumberPreferenceDialogFragment.max_day"
        private const val SAVE_STATE_CURRENT_DAY = "NumberPreferenceDialogFragment.current_day"

        fun newInstance(key: String) = NumberPreferenceDialogFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_KEY, key)
            }
        }
    }

    private var minValue: Int = 0
    private var maxValue: Int = 0
    private var currentValue: Int = 0
    private lateinit var numberPicker: NumberPicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            currentValue = PreferenceStore.getDays()
            minValue = 3
            maxValue = 21
        } else {
            minValue = savedInstanceState.getInt(SAVE_STATE_MIN_DAY)
            maxValue = savedInstanceState.getInt(SAVE_STATE_MAX_DAY)
            currentValue = savedInstanceState.getInt(SAVE_STATE_CURRENT_DAY)
        }
    }

    override fun onBindDialogView(view: View) {
        super.onBindDialogView(view)
        numberPicker = view.findViewById(R.id.numberPicker)
        numberPicker.minValue = minValue
        numberPicker.maxValue = maxValue
        numberPicker.value = currentValue
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SAVE_STATE_MIN_DAY, minValue)
        outState.putInt(SAVE_STATE_MAX_DAY, maxValue)
        outState.putInt(SAVE_STATE_CURRENT_DAY, numberPicker.value)
    }

    override fun onDialogClosed(positiveResult: Boolean) {
        val pref = preference as? NumberPreference
        if (positiveResult) {
            pref?.setTime(numberPicker.value)
        }
    }
}