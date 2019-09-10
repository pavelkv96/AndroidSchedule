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
package com.github.pavelkv96.schedule.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.pavelkv96.schedule.data.storage.models.about.AboutItem
import com.github.pavelkv96.schedule.ui.holders.AboutHolder
import com.github.pavelkv96.schedule.utils.base.listeners.ItemClickListener

class AboutAdapter(
    private var items: List<AboutItem> = mutableListOf(),
    private val listener: ItemClickListener? = null
) : RecyclerView.Adapter<AboutHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AboutHolder {
        val from = LayoutInflater.from(parent.context)
        return AboutHolder(from.inflate(viewType, parent, false), listener)
    }

    override fun onBindViewHolder(holder: AboutHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position].description) {
            is String -> android.R.layout.simple_list_item_2
            else -> android.R.layout.simple_list_item_1
        }
    }

    fun setData(newItems: List<AboutItem>?) {
        items = newItems ?: mutableListOf()
        notifyDataSetChanged()
    }
}