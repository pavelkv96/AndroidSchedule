/*
 * Copyright (C) 2018 The Android Open Source Project
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

package com.github.pavelkv96.schedule

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.github.pavelkv96.schedule.ui.MainActivity
import com.github.pavelkv96.schedule.ui.flow.HomeFragment
import com.github.pavelkv96.schedule.ui.fragments.*
import com.github.pavelkv96.schedule.ui.fragments.about.AboutFragment
import com.github.pavelkv96.schedule.ui.fragments.teacher.TeachersListFragment
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val rule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun checkOpenedSettingsFragment() {
        val mainActivity = rule.activity
        val flowFragment = getCurrentFlowFragment<HomeFragment>(mainActivity!!)
        Assert.assertNotNull(flowFragment)
        Assert.assertEquals(flowFragment!!::class.java, HomeFragment::class.java)

        val drawer = withId(R.id.drawer_layout)
        val navigationView = withId(R.id.nav_view)

        // Open settings fragment
        onView(drawer).perform(DrawerActions.open())
        onView(navigationView).perform(NavigationViewActions.navigateTo(R.id.nav_settings))
        Thread.sleep(1000)

        val childFragment = getCurrentChildFlowFragment<SettingsFragment>(flowFragment)
        Assert.assertNotNull(childFragment)
        Assert.assertEquals(childFragment!!::class.java, SettingsFragment::class.java)

        rotateScreen(mainActivity, true)
        rotateScreen(mainActivity, false)
        Thread.sleep(1000)

        Assert.assertEquals(childFragment::class.java, SettingsFragment::class.java)
        onView(drawer).check(matches(DrawerMatchers.isClosed()))
    }

    @Test
    fun checkOpenedDepartmentListFragment() {
        val mainActivity = rule.activity
        val flowFragment = getCurrentFlowFragment<HomeFragment>(mainActivity!!)
        Assert.assertNotNull(flowFragment)
        Assert.assertEquals(flowFragment!!::class.java, HomeFragment::class.java)

        val drawer = withId(R.id.drawer_layout)
        val navigationView = withId(R.id.nav_view)

        // Open department list fragment
        onView(drawer).perform(DrawerActions.open())
        onView(navigationView).perform(NavigationViewActions.navigateTo(R.id.nav_group))
        Thread.sleep(1000)

        val childFragment = getCurrentChildFlowFragment<DepartmentsListFragment>(flowFragment)
        Assert.assertNotNull(childFragment)
        Assert.assertEquals(childFragment!!::class.java, DepartmentsListFragment::class.java)

        rotateScreen(mainActivity, true)
        rotateScreen(mainActivity, false)
        Thread.sleep(1000)

        Assert.assertEquals(childFragment::class.java, DepartmentsListFragment::class.java)
        onView(drawer).check(matches(DrawerMatchers.isClosed()))
    }

    @Test
    fun checkOpenedAboutFragment() {
        val mainActivity = rule.activity
        val flowFragment = getCurrentFlowFragment<HomeFragment>(mainActivity!!)
        Assert.assertNotNull(flowFragment)
        Assert.assertEquals(flowFragment!!::class.java, HomeFragment::class.java)

        val drawer = withId(R.id.drawer_layout)
        val navigationView = withId(R.id.nav_view)

        // Open about fragment
        onView(drawer).perform(DrawerActions.open())
        onView(navigationView).perform(NavigationViewActions.navigateTo(R.id.nav_about))
        Thread.sleep(1000)

        val childFragment = getCurrentChildFlowFragment<AboutFragment>(flowFragment)
        Assert.assertNotNull(childFragment)
        Assert.assertEquals(childFragment!!::class.java, AboutFragment::class.java)

        rotateScreen(mainActivity, true)
        rotateScreen(mainActivity, false)
        Thread.sleep(1000)

        Assert.assertEquals(childFragment::class.java, AboutFragment::class.java)
        onView(drawer).check(matches(DrawerMatchers.isClosed()))
    }

    @Test
    fun checkOpenedStudentFragment() {
        val mainActivity = rule.activity
        val flowFragment = getCurrentFlowFragment<HomeFragment>(mainActivity!!)
        Assert.assertNotNull(flowFragment)
        Assert.assertEquals(flowFragment!!::class.java, HomeFragment::class.java)

        val drawer = withId(R.id.drawer_layout)
        val navigationView = withId(R.id.nav_view)

        // Open student fragment
        onView(drawer).perform(DrawerActions.open())
        onView(navigationView).perform(NavigationViewActions.navigateTo(R.id.nav_student))
        Thread.sleep(1000)

        val childFragment = getCurrentChildFlowFragment<StudentFragment>(flowFragment)
        Assert.assertNotNull(childFragment)
        Assert.assertEquals(childFragment!!::class.java, StudentFragment::class.java)

        rotateScreen(mainActivity, true)
        rotateScreen(mainActivity, false)
        Thread.sleep(1000)

        Assert.assertEquals(childFragment::class.java, StudentFragment::class.java)
        onView(drawer).check(matches(DrawerMatchers.isClosed()))
    }

    @Test
    fun checkOpenedTeachersFragment() {
        val mainActivity = rule.activity
        val flowFragment = getCurrentFlowFragment<HomeFragment>(mainActivity!!)
        Assert.assertNotNull(flowFragment)
        Assert.assertEquals(flowFragment!!::class.java, HomeFragment::class.java)

        val drawer = withId(R.id.drawer_layout)
        val navigationView = withId(R.id.nav_view)

        // Open teachers list fragment
        onView(drawer).perform(DrawerActions.open())
        onView(navigationView).perform(NavigationViewActions.navigateTo(R.id.nav_teachers))
        Thread.sleep(1000)

        val childFragment = getCurrentChildFlowFragment<TeachersListFragment>(flowFragment)
        Assert.assertNotNull(childFragment)
        Assert.assertEquals(childFragment!!::class.java, TeachersListFragment::class.java)

        rotateScreen(mainActivity, true)
        rotateScreen(mainActivity, false)
        Thread.sleep(1000)

        Assert.assertEquals(childFragment::class.java, TeachersListFragment::class.java)
        onView(drawer).check(matches(DrawerMatchers.isClosed()))
    }

    @Test
    fun checkOpenedBookmarksFragment() {
        val mainActivity = rule.activity
        val flowFragment = getCurrentFlowFragment<HomeFragment>(mainActivity!!)
        Assert.assertNotNull(flowFragment)
        Assert.assertEquals(flowFragment!!::class.java, HomeFragment::class.java)

        val drawer = withId(R.id.drawer_layout)
        val navigationView = withId(R.id.nav_view)

        // Open bookmarks fragment
        onView(drawer).perform(DrawerActions.open())
        onView(navigationView).perform(NavigationViewActions.navigateTo(R.id.nav_bookmarks))
        Thread.sleep(1000)

        val childFragment = getCurrentChildFlowFragment<BookmarksFragment>(flowFragment)
        Assert.assertNotNull(childFragment)
        Assert.assertEquals(childFragment!!::class.java, BookmarksFragment::class.java)

        rotateScreen(mainActivity, true)
        rotateScreen(mainActivity, false)
        Thread.sleep(1000)

        Assert.assertEquals(childFragment::class.java, BookmarksFragment::class.java)
        onView(drawer).check(matches(DrawerMatchers.isClosed()))
    }
}
