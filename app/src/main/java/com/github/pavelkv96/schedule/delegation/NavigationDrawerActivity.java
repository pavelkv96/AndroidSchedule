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

package com.github.pavelkv96.schedule.delegation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import butterknife.BindView;
import com.github.pavelkv96.schedule.R;
import com.github.pavelkv96.schedule.base.BaseDelegationActivity;
import com.github.pavelkv96.schedule.delegation.NavigationDrawerContract.NavigationDrawerView;

/**
 * Created by Pavel on 15.09.2018.
 */
public class NavigationDrawerActivity extends BaseDelegationActivity<
        NavigationDrawerView, NavigationDrawerPresenter, NavigationDrawerDelegate>
        implements NavigationDrawerView {

    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;

    @Override
    protected NavigationDrawerDelegate instantiateDelegateInstance() {
        return new NavigationDrawerDelegate();
    }

    @NonNull
    @Override
    protected NavigationDrawerPresenter getPresenterInstance() {
        return new NavigationDrawerPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_home);
        setSupportActionBar(mToolbar);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        if (!mDelegate.closeDrawer()) {
            super.onBackPressed();
        }
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public NavigationDrawerActivity getActivity() {
        return this;
    }
}
