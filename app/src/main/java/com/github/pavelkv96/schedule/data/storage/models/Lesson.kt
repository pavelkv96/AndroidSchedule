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
package com.github.pavelkv96.schedule.data.storage.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Lesson(
    @SerializedName("timeStart") val timeStart: String?,
    @SerializedName("timeEnd") val timeEnd: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("address") val address: String?,
    @SerializedName("room") val room: String?,
    @SerializedName("groups") private val _groups: List<Group>?,
    @SerializedName("subgroup") val subgroup: Subgroup?,
    @SerializedName("teacher") val teacher: Teacher?
) : Parcelable {
    val groups: String?
        get() {
            val groupsList = StringBuilder()
            if (_groups != null) {
                for (i in _groups.indices) {
                    groupsList.append(_groups[i].title)
                    if (i + 1 != _groups.size) {
                        groupsList.append("\n")
                    }
                }
                return groupsList.toString()
            }
            return null
        }
}
