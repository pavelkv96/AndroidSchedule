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

package com.github.pavelkv96.schedule.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Student extends BaseEntity implements Parcelable {

    @SerializedName("fullname")
    private String fullname;
    @SerializedName("grouptitle")
    private String grouptitle;
    @SerializedName("studenttype")
    private String studenttype;
    @SerializedName("nzach")
    private String nzach;

    public String getFullname() {
        return fullname;
    }

    public String getGrouptitle() {
        return grouptitle;
    }

    public String getStudenttype() {
        return studenttype;
    }

    public String getNzach() {
        return nzach;
    }

    private Student(Parcel in) {
        super(in);
        id = in.readInt();
        fullname = in.readString();
        grouptitle = in.readString();
        studenttype = in.readString();
        nzach = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(id);
        dest.writeString(fullname);
        dest.writeString(grouptitle);
        dest.writeString(studenttype);
        dest.writeString(nzach);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
