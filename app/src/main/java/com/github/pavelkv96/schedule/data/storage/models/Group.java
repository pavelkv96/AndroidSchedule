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

public class Group extends BaseEntity implements Parcelable {

    @SerializedName("title")
    private String title;
    @SerializedName("course")
    private int course;
    @SerializedName("students")
    private int students;
    @SerializedName("department")
    private Department department;
    @SerializedName("faculty")
    private Faculty faculty;

    public String getTitle() {
        return title;
    }

    public Integer getCourse() {
        return course;
    }

    public Integer getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return title;
    }

    private Group(Parcel in) {
        super(in);
        id = in.readInt();
        title = in.readString();
        course = in.readInt();
        students = in.readInt();
        department = in.readParcelable(Department.class.getClassLoader());
        faculty = in.readParcelable(Faculty.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeInt(course);
        dest.writeInt(students);
        dest.writeParcelable(department, flags);
        dest.writeParcelable(faculty, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Group> CREATOR = new Creator<Group>() {
        @Override
        public Group createFromParcel(Parcel in) {
            return new Group(in);
        }

        @Override
        public Group[] newArray(int size) {
            return new Group[size];
        }
    };

}
