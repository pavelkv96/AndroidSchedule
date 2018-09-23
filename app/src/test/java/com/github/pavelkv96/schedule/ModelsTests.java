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
import com.github.pavelkv96.schedule.models.Groups;
import com.github.pavelkv96.schedule.models.Schedule;
import com.github.pavelkv96.schedule.utils.Mocks;
import java.io.IOException;
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

    @Test
    public void testScheduleModel() {
        //Schedule response = Mocks.getGsonObject("group_schedule_by_group_id.json", Schedule.class);
//        new Gson().fromJson(Mocks.stream("group_schedule_by_group_id.json"), Schedule.class);
//        Call<Schedule> call = Calls.response(response);
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
        Groups groups = null;
        Call<Groups> call = Mocks.getCall("groups.json", Groups.class);
        Mockito.when(mockScheduleApi.getGroups(0, 0, 0, "")).thenReturn(call);
        Call<Groups> groupsCall = mockScheduleApi.getGroups(0, 0, 0, "");

        try {
            groups = groupsCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (groups != null) {
            Assert.assertEquals(11, groups.getItems().size());
        } else {
            throw new NullPointerException("Object Groups is null");
        }
    }
}