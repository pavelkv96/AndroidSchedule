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

public class Department extends BaseEntity implements Parcelable {
	
	private String title;

	private Department(Parcel in) {
		super(in);
		id = in.readInt();
		title = in.readString();
	}

	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		return title;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		dest.writeInt(id);
		dest.writeString(title);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Department> CREATOR = new Creator<Department>() {
		@Override
		public Department createFromParcel(Parcel in) {
			return new Department(in);
		}

		@Override
		public Department[] newArray(int size) {
			return new Department[size];
		}
	};
}
