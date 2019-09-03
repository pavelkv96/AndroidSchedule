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
package com.github.pavelkv96.schedule.data.storage.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Lesson implements Parcelable {

    @SerializedName("timeStart")
    private String timeStart;
    @SerializedName("timeEnd")
    private String timeEnd;
    @SerializedName("type")
    private String type;
    @SerializedName("title")
    private String title;
    @SerializedName("address")
    private String address;
    @SerializedName("room")
    private String room;
    @SerializedName("groups")
    private List<Group> groups;
    @SerializedName("subgroup")
    private Subgroup subgroup;
    @SerializedName("teacher")
    private Teacher teacher;

    public String getTimeStart() {
        return timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public String getRoom() {
        return room;
    }

    public String getGroups() {
        StringBuilder groupsList = new StringBuilder();
        if (groups != null) {
            for (int i = 0; i < groups.size(); i++) {
                groupsList.append(groups.get(i).getTitle());
                if (i + 1 != groups.size()) {
                    groupsList.append("\n");
                }
            }
            return groupsList.toString();
        }
        return null;
    }

    public Subgroup getSubgroup() {
        return subgroup;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    private Lesson(Parcel in) {
        timeStart = in.readString();
        timeEnd = in.readString();
        type = in.readString();
        title = in.readString();
        address = in.readString();
        room = in.readString();
        groups = in.createTypedArrayList(Group.CREATOR);
        subgroup = in.readParcelable(Subgroup.class.getClassLoader());
        teacher = in.readParcelable(Teacher.class.getClassLoader());
    }

    public static final Creator<Lesson> CREATOR = new Creator<Lesson>() {
        @Override
        public Lesson createFromParcel(Parcel in) {
            return new Lesson(in);
        }

        @Override
        public Lesson[] newArray(int size) {
            return new Lesson[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(timeStart);
        dest.writeString(timeEnd);
        dest.writeString(type);
        dest.writeString(title);
        dest.writeString(address);
        dest.writeString(room);
        dest.writeTypedList(groups);
        dest.writeParcelable(subgroup, flags);
        dest.writeParcelable(teacher, flags);
    }
}
