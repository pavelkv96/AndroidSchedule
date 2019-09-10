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
import androidx.lifecycle.ViewModel
import com.github.pavelkv96.schedule.data.storage.models.about.AboutItem

class AboutViewModel : ViewModel() {

    private var data: LiveData<List<AboutItem>>? = null

    fun getData(): LiveData<List<AboutItem>>? {
        if (data == null) {
            data = AboutRepository.getFirstData()
        }
        return data
    }

    override fun onCleared() {
        data = null
        super.onCleared()
    }
}