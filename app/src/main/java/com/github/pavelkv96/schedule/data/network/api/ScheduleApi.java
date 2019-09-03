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
package com.github.pavelkv96.schedule.data.network.api;

import com.github.pavelkv96.schedule.data.storage.models.Department;
import com.github.pavelkv96.schedule.data.storage.models.Entities;
import com.github.pavelkv96.schedule.data.storage.models.Faculty;
import com.github.pavelkv96.schedule.data.storage.models.Group;
import com.github.pavelkv96.schedule.data.storage.models.Schedule;
import com.github.pavelkv96.schedule.data.storage.models.Student;
import com.github.pavelkv96.schedule.data.storage.models.Teacher;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Pavel on 15.09.2018.
 */
public interface ScheduleApi {

    @GET("getDepartments")
    Call<Entities<Department>> getDepartments();

    @GET("getDepartments")
    Call<Entities<Department>> getDepartments(@Query("lang") String lang);

    @GET("getFaculties")
    Call<Entities<Faculty>> getFaculties(@Query("lang") String lang);

    @GET("getGroups")
    Call<Entities<Group>> getGroups(@Query("departmentId") int departmentId, @Query("facultyId") int facultyId, @Query("course") int course, @Query("lang") String lang);

    @GET("getTeachers?sort=surname,name&fields=surname,name,patronym,post")
    Call<Entities<Teacher>> getTeachers(@Query("lang") String lang);

    @GET("getStudent")
    Call<Student> getStudent(@Query("login") String login, @Query("lang") String lang);

    @GET("getTeacherSchedule")
    Call<Schedule> getTeacherSchedule(@Query("teacherId") int scheduleId, @Query("dateStart") String dateStart, @Query("dateEnd") String dateEnd, @Query("lang") String lang);

    @GET("getGroupSchedule")
    Call<Schedule> getStudentSchedule(@Query("studentId") int scheduleId, @Query("dateStart") String dateStart, @Query("dateEnd") String dateEnd, @Query("lang") String lang);

    @GET("getGroupSchedule")
    Call<Schedule> getGroupSchedule(@Query("groupId") int scheduleId, @Query("dateStart") String dateStart, @Query("dateEnd") String dateEnd, @Query("lang") String lang);
}
