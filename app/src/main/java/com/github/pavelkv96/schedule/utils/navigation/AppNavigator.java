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
package com.github.pavelkv96.schedule.utils.navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.util.Log;
import java.io.Serializable;
import java.util.LinkedList;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.support.SupportAppScreen;
import ru.terrakok.cicerone.commands.Back;
import ru.terrakok.cicerone.commands.BackTo;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Forward;
import ru.terrakok.cicerone.commands.Replace;

public class AppNavigator implements Navigator, Serializable {

    private transient final Activity activity;
    private final String fragment;
    private transient FragmentManager fragmentManager;
    private final int containerId;
    private LinkedList<String> localStackCopy;
    private final String TAG = AppNavigator.class.getSimpleName();

    public AppNavigator(FragmentActivity activity, int containerId) {
        this(activity, activity.getSupportFragmentManager(), containerId, null);
    }

    public AppNavigator(FragmentActivity activity, FragmentManager manager, int containerId) {
        this(activity, manager, containerId, null);
    }

    public AppNavigator(FragmentActivity activity, FragmentManager fragmentManager, int containerId,
                        String fragment) {
        this.activity = activity;
        this.fragmentManager = fragmentManager;
        this.containerId = containerId;
        this.fragment = fragment;
    }

    @Override
    public void applyCommands(Command[] commands) {
        fragmentManager.executePendingTransactions();

        //copy stack before apply commands
        copyStackToLocal();

        for (Command command : commands) {
            applyCommand(command);
        }

        Log.e(TAG, fragment + "------------------after----------------");

        for (int i = 0; i < localStackCopy.size(); i++) {
            Log.e(TAG, "copyStackToLocal: size " + localStackCopy.size() + "     " + localStackCopy
                    .get(i));
        }

        Log.e(TAG, fragment + "---------------------------------------");
    }

    private void copyStackToLocal() {
        localStackCopy = new LinkedList<>();

        final int stackSize = fragmentManager.getBackStackEntryCount();

        Log.e(TAG, fragment + "------------------before----------------");

        for (int i = 0; i < stackSize; i++) {
            localStackCopy.add(fragmentManager.getBackStackEntryAt(i).getName());
            Log.e(TAG, "copyStackToLocal: size " + localStackCopy.size() + "     " + localStackCopy
                    .get(i));
        }
    }

    protected void applyCommand(Command command) {
        if (command instanceof Forward) {
            activityForward((Forward) command);
        } else if (command instanceof Replace) {
            activityReplace((Replace) command);
        } else if (command instanceof BackTo) {
            backTo((BackTo) command);
        } else if (command instanceof Back) {
            fragmentBack();
        }
    }


    protected void activityForward(Forward command) {
        SupportAppScreen screen = (SupportAppScreen) command.getScreen();
        Intent activityIntent = screen.getActivityIntent(activity);

        // Start activity
        if (activityIntent != null) {
            checkAndStartActivity(screen, activityIntent);
        } else {
            fragmentForward(command);
        }
    }

    protected void fragmentForward(Forward command) {
        SupportAppScreen screen = (SupportAppScreen) command.getScreen();
        Fragment fragment = createFragment(screen);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        setupFragmentTransaction(
                command,
                fragmentManager.findFragmentById(containerId),
                fragment,
                fragmentTransaction
        );

        fragmentTransaction
                .replace(containerId, fragment)
                .addToBackStack(screen.getScreenKey())
                .commit();
        localStackCopy.add(screen.getScreenKey());
    }

    protected void fragmentBack() {
        if (localStackCopy.size() > 0) {
            fragmentManager.popBackStack();
            localStackCopy.removeLast();
        } else {
            activityBack();
        }
    }

    protected void activityBack() {
        activity.finish();
    }

    protected void activityReplace(Replace command) {
        SupportAppScreen screen = (SupportAppScreen) command.getScreen();
        Intent activityIntent = screen.getActivityIntent(activity);

        // Replace activity
        if (activityIntent != null) {
            checkAndStartActivity(screen, activityIntent);
            activity.finish();
        } else {
            fragmentReplace(command);
        }
    }

    protected void fragmentReplace(Replace command) {
        SupportAppScreen screen = (SupportAppScreen) command.getScreen();
        Fragment fragment = createFragment(screen);

        if (localStackCopy.size() > 0) {
            fragmentManager.popBackStack();
            localStackCopy.removeLast();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            setupFragmentTransaction(
                    command,
                    fragmentManager.findFragmentById(containerId),
                    fragment,
                    fragmentTransaction
            );

            fragmentTransaction
                    .replace(containerId, fragment)
                    .addToBackStack(screen.getScreenKey())
                    .commit();
            localStackCopy.add(screen.getScreenKey());

        } else {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            setupFragmentTransaction(
                    command,
                    fragmentManager.findFragmentById(containerId),
                    fragment,
                    fragmentTransaction
            );

            fragmentTransaction
                    .replace(containerId, fragment)
                    .commit();
        }
    }

    protected void backTo(BackTo command) {
        if (command.getScreen() == null) {
            backToRoot();
        } else {
            String key = command.getScreen().getScreenKey();
            int index = localStackCopy.indexOf(key);
            int size = localStackCopy.size();

            if (index != -1) {
                for (int i = 1; i < size - index; i++) {
                    localStackCopy.removeLast();
                }
                fragmentManager.popBackStack(key, 0);
            } else {
                backToUnexisting((SupportAppScreen) command.getScreen());
            }
        }
    }

    private void backToRoot() {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        localStackCopy.clear();
    }

    protected void setupFragmentTransaction(Command command,
                                            Fragment currentFragment,
                                            Fragment nextFragment,
                                            FragmentTransaction fragmentTransaction) {
    }

    protected Bundle createStartActivityOptions(Command command, Intent activityIntent) {
        return null;
    }

    private void checkAndStartActivity(SupportAppScreen screen, Intent activityIntent) {
        // Check if we can start activity
        if (activityIntent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivity(activityIntent);
        } else {
            unExistingActivity(screen, activityIntent);
        }
    }

    protected void unExistingActivity(SupportAppScreen screen, Intent activityIntent) {
        // Do nothing by default
    }

    protected Fragment createFragment(SupportAppScreen screen) {
        Fragment fragment = screen.getFragment();

        if (fragment == null) {
            errorWhileCreatingScreen(screen);
        }
        return fragment;
    }

    protected void backToUnexisting(SupportAppScreen screen) {
        backToRoot();
    }

    protected void errorWhileCreatingScreen(SupportAppScreen screen) {
        throw new RuntimeException("Can't create a screen: " + screen.getScreenKey());
    }

    public LinkedList<String> getStackList() {
        return localStackCopy;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }
}


