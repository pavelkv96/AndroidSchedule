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
package com.github.pavelkv96.schedule.ui.fragments.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.pavelkv96.schedule.R
import com.github.pavelkv96.schedule.data.storage.models.about.AboutItem

object AboutRepository {

    fun getFirstData(): LiveData<List<AboutItem>> {
        return MutableLiveData<List<AboutItem>>().apply {
            val list: MutableList<AboutItem> = mutableListOf()
            list.apply {
                add(
                    AboutItem(
                        R.string.title_about_open_url,
                        R.string.description_about_open_url
                    )
                )
                add(
                    AboutItem(
                        R.string.title_about_contact_developer,
                        R.string.description_about_contact_developer
                    )
                )
                add(
                    AboutItem(
                        R.string.title_about_rate_app,
                        R.string.description_about_rate_app
                    )
                )
                add(
                    AboutItem(
                        R.string.title_about_version_app,
                        R.string.description_about_version_app
                    )
                )
            }
            postValue(list)
        }
    }
}