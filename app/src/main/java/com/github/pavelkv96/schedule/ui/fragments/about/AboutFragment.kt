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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.pavelkv96.schedule.R
import com.github.pavelkv96.schedule.ui.adapters.AboutAdapter
import com.github.pavelkv96.schedule.utils.base.BaseFragment
import com.github.pavelkv96.schedule.utils.base.listeners.ItemClickListener

class AboutFragment : BaseFragment(), ItemClickListener {
    override fun getLayout(): Int = R.layout.fragment_about

    private lateinit var model: AboutViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProviders.of(this).get(AboutViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView?>(R.id.rv_about)
        val progressBar = view.findViewById<ProgressBar?>(R.id.progress_bar)
        val aboutAdapter = AboutAdapter(listener = this)

        recyclerView?.apply {
            adapter = aboutAdapter
            setHasFixedSize(true)
            val manager = LinearLayoutManager(context)
            layoutManager = manager
            addItemDecoration(DividerItemDecoration(context, manager.orientation))
        }
        model.getData()?.observe(this, Observer {
            progressBar?.visibility = View.GONE
            aboutAdapter.setData(it)
            recyclerView?.visibility = View.VISIBLE
        })
        return view
    }

    override fun onItemClick(view: View, position: Int) {
        Toast.makeText(context, "Click by position: $position", Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = AboutFragment()
    }
}