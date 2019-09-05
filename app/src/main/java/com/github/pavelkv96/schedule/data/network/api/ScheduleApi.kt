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
package com.github.pavelkv96.schedule.data.network.api

import com.github.pavelkv96.schedule.data.storage.models.Department
import com.github.pavelkv96.schedule.data.storage.models.Entities
import com.github.pavelkv96.schedule.data.storage.models.Faculty
import com.github.pavelkv96.schedule.data.storage.models.Group
import com.github.pavelkv96.schedule.data.storage.models.Schedule
import com.github.pavelkv96.schedule.data.storage.models.Student
import com.github.pavelkv96.schedule.data.storage.models.Teacher
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Pavel on 15.09.2018.
 */
interface ScheduleApi {

    @GET("getDepartments")
    fun getDepartments(
        @Query("lang") lang: String
    ): Call<Entities<Department>>

    @GET("getFaculties")
    fun getFaculties(
        @Query("lang") lang: String
    ): Call<Entities<Faculty>>

    @GET("getGroups")
    fun getGroups(
        @Query("departmentId") departmentId: Int,
        @Query("facultyId") facultyId: Int,
        @Query("course") course: Int,
        @Query("lang") lang: String
    ): Call<Entities<Group>>

    @GET("getTeachers?sort=surname,name&fields=surname,name,patronym,post")
    fun getTeachers(
        @Query("lang") lang: String
    ): Call<Entities<Teacher>>

    @GET("getStudent")
    fun getStudent(
        @Query("login") login: String,
        @Query("lang") lang: String
    ): Call<Student>

    @GET("getTeacherSchedule")
    fun getTeacherSchedule(
        @Query("teacherId") scheduleId: Int,
        @Query("dateStart") dateStart: String,
        @Query("dateEnd") dateEnd: String,
        @Query("lang") lang: String
    ): Call<Schedule>

    @GET("getGroupSchedule")
    fun getStudentSchedule(
        @Query("studentId") scheduleId: Int,
        @Query("dateStart") dateStart: String,
        @Query("dateEnd") dateEnd: String,
        @Query("lang") lang: String
    ): Call<Schedule>

    @GET("getGroupSchedule")
    fun getGroupSchedule(
        @Query("groupId") scheduleId: Int,
        @Query("dateStart") dateStart: String,
        @Query("dateEnd") dateEnd: String,
        @Query("lang") lang: String
    ): Call<Schedule>
}
