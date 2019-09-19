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
package com.github.pavelkv96.schedule.ui.fragments.teacher

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.pavelkv96.schedule.App
import com.github.pavelkv96.schedule.R
import com.github.pavelkv96.schedule.data.network.restmodels.Entities
import com.github.pavelkv96.schedule.data.network.restmodels.Teacher
import com.github.pavelkv96.schedule.ui.adapters.TeachersAdapter
import com.github.pavelkv96.schedule.utils.base.BaseFragment
import com.github.pavelkv96.schedule.utils.base.listeners.ItemClickListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeachersListFragment : BaseFragment(), ItemClickListener, Callback<Entities<Teacher>> {

    override fun onResponse(call: Call<Entities<Teacher>>, response: Response<Entities<Teacher>>) {
        if (response.isSuccessful)
            teachersAdapter.setData(response.body())
    }

    override fun onFailure(call: Call<Entities<Teacher>>, t: Throwable) = Unit

    override fun getLayout(): Int = R.layout.fragment_teachers

    private lateinit var teachersAdapter: TeachersAdapter
    private val keyArray: String = "key_teachers_list"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            teachersAdapter = TeachersAdapter(listener = this)
            teachersAdapter.setData(savedInstanceState.getParcelableArrayList(keyArray))
        } else {
            teachersAdapter = TeachersAdapter(listener = this)
            App.instance.getNetwork().getTeachers("ru_RU").enqueue(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.rv_teacher).apply {
            val layout = LinearLayoutManager(context)
            layoutManager = layout
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, layout.orientation))
            adapter = teachersAdapter
        }
        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(keyArray, teachersAdapter.getData())
    }

    override fun onItemClick(view: View, position: Int) {
        Toast.makeText(context, "Position: $position", Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = TeachersListFragment()
    }
}