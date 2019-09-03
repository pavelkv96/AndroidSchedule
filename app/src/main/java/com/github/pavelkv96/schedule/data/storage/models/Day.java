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

public class Day implements Parcelable {

    @SerializedName("num")
    private int num;
    @SerializedName("count")
    private int count;
    @SerializedName("date")
    private String date;
    @SerializedName("lessons")
    private List<Lesson> lessons;

    public int getNum() {
        return num;
    }

    public int getCount() {
        return count;
    }

    public String getDate() {
        return date;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    private Day(Parcel in) {
        num = in.readInt();
        count = in.readInt();
        date = in.readString();
        lessons = in.createTypedArrayList(Lesson.CREATOR);
    }

    public static final Creator<Day> CREATOR = new Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel in) {
            return new Day(in);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(num);
        dest.writeInt(count);
        dest.writeString(date);
        dest.writeTypedList(lessons);
    }
}
