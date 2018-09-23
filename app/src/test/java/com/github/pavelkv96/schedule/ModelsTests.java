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

package com.github.pavelkv96.schedule;

import static org.mockito.Mockito.when;

import com.github.pavelkv96.schedule.api.ScheduleApi;
import com.github.pavelkv96.schedule.models.Department;
import com.github.pavelkv96.schedule.models.Entities;
import com.github.pavelkv96.schedule.models.Faculty;
import com.github.pavelkv96.schedule.models.Group;
import com.github.pavelkv96.schedule.models.Schedule;
import com.github.pavelkv96.schedule.utils.Mocks;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import retrofit2.Call;

@RunWith(MockitoJUnitRunner.class)
public class ModelsTests {

    private ScheduleApi mockScheduleApi;

    @Before
    public void setUp() {
        mockScheduleApi = Mockito.mock(ScheduleApi.class);
    }

    @After
    public void cleanUp(){
        mockScheduleApi = null;
    }

    @Test
    public void testTeacherScheduleModel() {
        Schedule schedule = null;
        Call<Schedule> call = Mocks.getCall("group_schedule_by_group_id.json", Schedule.class);
        when(mockScheduleApi.getTeacherSchedule(0, "", "", "")).thenReturn(call);
        Call<Schedule> scheduleCall = mockScheduleApi.getTeacherSchedule(0, "", "", "");

        try {
            schedule = scheduleCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (schedule != null) {
            int id = schedule.getDays().get(1).getLessons().get(1).getTeacher().getId();
            Assert.assertEquals(14785, id);
        } else {
            throw new NullPointerException("Object schedule is null");
        }
    }

    @Test
    public void testGroupsModel() {
        Entities<Group> groups = null;

        Type type = TypeToken.getParameterized(Entities.class, Group.class).getType();

        Call<Entities<Group>> call = Mocks.getCall("groups.json", type);
        when(mockScheduleApi.getGroups(0, 0, 0, "")).thenReturn(call);
        Call<Entities<Group>> groupsCall = mockScheduleApi.getGroups(0, 0, 0, "");

        try {
            groups = groupsCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (groups != null) {
            Assert.assertEquals(11, groups.getItems().size());
            Assert.assertEquals("СДП-ИСИТ-171", groups.getItems().get(6).getTitle());
        } else {
            throw new NullPointerException("Object Group is null");
        }
    }

    @Test
    public void testFacultiesModel() {
        Entities<Faculty> faculty = null;

        Type type = TypeToken.getParameterized(Entities.class, Faculty.class).getType();

        Call<Entities<Faculty>> call = Mocks.getCall("faculties.json", type);
        when(mockScheduleApi.getFaculties("")).thenReturn(call);
        Call<Entities<Faculty>> groupsCall = mockScheduleApi.getFaculties("");

        try {
            faculty = groupsCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (faculty != null) {
            Assert.assertEquals(18, faculty.getItems().size());
            Assert.assertEquals("Факультет экономики и управления", faculty.getItems().get(14).getTitle());
        } else {
            throw new NullPointerException("Object Faculty is null");
        }
    }

    @Test
    public void testDepartmentsModel(){
        Entities<Department> department = null;

        Type type = TypeToken.getParameterized(Entities.class, Department.class).getType();

        Call<Entities<Department>> call = Mocks.getCall("departments.json", type);
        when(mockScheduleApi.getDepartments("")).thenReturn(call);
        Call<Entities<Department>> departmentCall = mockScheduleApi.getDepartments("");

        try {
            department = departmentCall.execute().body();
        }catch (IOException e){
            e.printStackTrace();
        }

        if (department != null) {
            Assert.assertEquals(4, department.getItems().size());
            Assert.assertEquals("вечерняя форма", department.getItems().get(2).getTitle());
        } else {
            throw new NullPointerException("Object Department is null");
        }
    }
}
