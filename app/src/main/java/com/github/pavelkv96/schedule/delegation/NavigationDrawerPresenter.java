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

import com.github.pavelkv96.schedule.base.BasePresenterImpl;
import com.github.pavelkv96.schedule.delegation.NavigationDrawerContract.NavigationDrawerView;

/**
 * Created by Pavel on 15.09.2018.
 */
public class NavigationDrawerPresenter extends BasePresenterImpl<NavigationDrawerView>
        implements NavigationDrawerContract.NavigationDrawerPresenter {

    @Override
    public void getItemClick(CharSequence name) {
        getView().showToastMessage(name.toString());
    }
}
