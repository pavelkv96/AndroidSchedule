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

public class Teacher extends BaseEntity implements Parcelable {

    private String name;
    private String surname;
    private String patronym;
    private String fullname;
    private String post;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronym() {
        return patronym;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPost() {
        return post;
    }

    @Override
    public String toString() {
        return surname + " " + name + " " + patronym;
    }

    private Teacher(Parcel in) {
        super(in);
        id = in.readInt();
        name = in.readString();
        surname = in.readString();
        patronym = in.readString();
        fullname = in.readString();
        post = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeString(patronym);
        dest.writeString(fullname);
        dest.writeString(post);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Teacher> CREATOR = new Creator<Teacher>() {
        @Override
        public Teacher createFromParcel(Parcel in) {
            return new Teacher(in);
        }

        @Override
        public Teacher[] newArray(int size) {
            return new Teacher[size];
        }
    };

}
