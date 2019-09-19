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
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.github.pavelkv96.schedule.R
import com.github.pavelkv96.schedule.data.network.restmodels.Entities
import com.github.pavelkv96.schedule.data.network.restmodels.Teacher
import com.github.pavelkv96.schedule.ui.holders.TeacherHolder
import com.github.pavelkv96.schedule.utils.base.listeners.ItemClickListener

class TeachersAdapter(
    private var items: List<Teacher> = mutableListOf(),
    private val listener: ItemClickListener
) : Adapter<TeacherHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherHolder {
        val from = LayoutInflater.from(parent.context)
        val view = from.inflate(R.layout.item_description, parent, false)
        return TeacherHolder(view, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TeacherHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setData(entities: Entities<Teacher>?) {
        items = entities?.items ?: mutableListOf()
        notifyDataSetChanged()
    }

    fun setData(entities: List<Teacher>?) {
        items = entities ?: mutableListOf()
        notifyDataSetChanged()
    }

    fun getData(): ArrayList<Teacher> {
        return items as ArrayList
    }
}