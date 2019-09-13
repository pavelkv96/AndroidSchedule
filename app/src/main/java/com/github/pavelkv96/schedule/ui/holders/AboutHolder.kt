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
package com.github.pavelkv96.schedule.ui.holders

import android.view.View
import android.widget.TextView
import com.github.pavelkv96.schedule.BuildConfig
import com.github.pavelkv96.schedule.data.storage.models.about.AboutItem
import com.github.pavelkv96.schedule.utils.base.BaseViewHolder
import com.github.pavelkv96.schedule.utils.base.listeners.ItemClickListener

class AboutHolder(
    itemView: View, listener: ItemClickListener? = null
) : BaseViewHolder<AboutItem>(itemView, listener) {

    private val title: TextView? = itemView.findViewById(android.R.id.text1)
    private val description: TextView? = itemView.findViewById(android.R.id.text2)

    override fun bind(item: AboutItem) {
        val context = itemView.context
        title?.text = context.getString(item.title)
        val s = context.getString(item.description)
        description?.text = String.format(s, BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE)
    }
}