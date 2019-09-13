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

import android.content.Context
import android.content.res.TypedArray
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import androidx.preference.DialogPreference
import com.github.pavelkv96.schedule.R
import com.github.pavelkv96.schedule.data.storage.preferenses.PreferenceStore

class NumberPreference(
    context: Context, attrs: AttributeSet
) : DialogPreference(context, attrs) {

    private var mTime: Int = 3

    override fun getDialogLayoutResource(): Int {
        return R.layout.numberpicker_dialog
    }

    fun setTime(time: Int) {
        val changed = mTime != time
        if (changed){
            mTime = time
            persistInt(time)
        }
    }

    fun getTime() = mTime

    override fun onGetDefaultValue(a: TypedArray, index: Int): Any? {
        return a.getInt(index, 10)
    }

    override fun onSetInitialValue(defaultValue: Any?) {
        setTime(getPersistedInt((defaultValue as? Int)?:PreferenceStore.getDays()))
    }

    override fun onSaveInstanceState(): Parcelable {
        val superState = super.onSaveInstanceState()
        if (isPersistent) {
            return superState
        }

        val myState = SavedState(superState)
        myState.value = getTime()
        return myState
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state == null || state.javaClass != SavedState::class.java) {
            super.onRestoreInstanceState(state)
            return
        }

        val myState = state as SavedState
        super.onRestoreInstanceState(myState.superState)
        setTime(myState.value)
    }

    class SavedState : BaseSavedState {
        var value: Int = 3

        constructor(superState: Parcelable) : super(superState)

        override fun writeToParcel(dest: Parcel, flags: Int) {
            super.writeToParcel(dest, flags)
            dest.writeInt(value)
        }

        private constructor(`in`: Parcel) : super(`in`) {
            value = `in`.readInt()
        }

        companion object {
            @JvmField
            val CREATOR: Parcelable.Creator<SavedState> = object : Parcelable.Creator<SavedState> {
                override fun createFromParcel(`in`: Parcel): SavedState {
                    return SavedState(`in`)
                }

                override fun newArray(size: Int): Array<SavedState?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }
}
