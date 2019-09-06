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
package com.github.pavelkv96.schedule.ui.flow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.github.pavelkv96.schedule.R
import com.github.pavelkv96.schedule.utils.base.BaseFlowFragment
import com.github.pavelkv96.schedule.utils.navigation.Screens
import com.google.android.material.navigation.NavigationView
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener

class HomeFragment : BaseFlowFragment(), OnNavigationItemSelectedListener {

    private lateinit var mDrawerLayout: DrawerLayout

    override fun start(): Screens = Screens(Screens.main_fragment)

    override fun getLayout(): Int = R.layout.fragment_flow_home

    override fun tag(): String = HomeFragment::class.java.simpleName

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        val mToolBar = view.findViewById<Toolbar>(R.id.toolbar)
        mDrawerLayout = view.findViewById(R.id.drawer_layout)
        val mNavigationView = view.findViewById<NavigationView>(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
                activity,
                mDrawerLayout,
                mToolBar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        )
        mDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        mNavigationView.setNavigationItemSelectedListener(this)
        return view
    }

    override fun onBackPressed(): Boolean {
        if (!closeDrawer()){
            return super.onBackPressed()
        }
        return true
    }

    private fun closeDrawer(): Boolean {
        return if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START)
            true
        } else {
            false
        }
    }

    override fun onNavigationItemSelected(menu: MenuItem): Boolean {
        when (menu.itemId) {
            R.id.nav_bookmarks -> Screens.main_fragment
            R.id.nav_teachers -> Screens.teachers_fragment
            R.id.nav_student -> Screens.student_fragment
            R.id.nav_group -> Screens.departments_fragment
            R.id.nav_settings -> Screens.settings_fragment
            R.id.nav_about -> Screens.about_fragment
            else -> throw IllegalArgumentException("Not implemented navigation item: ${menu.title}")
        }.apply {
            router.newRootChain(Screens(this))
        }

        closeDrawer()
        return true
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}