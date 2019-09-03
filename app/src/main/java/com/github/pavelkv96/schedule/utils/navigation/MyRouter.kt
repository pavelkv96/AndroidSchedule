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
package com.github.pavelkv96.schedule.utils.navigation

import ru.terrakok.cicerone.BaseRouter
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.commands.*

class MyRouter : BaseRouter() {

    fun navigateTo(screen: Screen) {
        executeCommands(Forward(screen))
    }

    fun newRootScreen(screen: Screen) {
        executeCommands(
            BackTo(null),
            Forward(screen)
        )
    }

    fun replaceScreen(screen: Screen) {
        executeCommands(Replace(screen))
    }

    fun backTo(screen: Screen) {
        executeCommands(BackTo(screen))
    }

    fun newChain(vararg screens: Screen) {
        val commands = arrayOfNulls<Command>(screens.size)
        for (i in commands.indices) {
            commands[i] = Forward(screens[i])
        }
        executeCommands(*commands)
    }

    fun newRootChain(vararg screens: Screen) {
        val commands = arrayOfNulls<Command>(screens.size + 1)
        commands[0] = BackTo(null)
        if (screens.isNotEmpty()) {
            commands[1] = Replace(screens[0])
            for (i in 1 until screens.size) {
                commands[i + 1] = Forward(screens[i])
            }
        }
        executeCommands(*commands)
    }

    fun finishChain() {
        executeCommands(
            BackTo(null),
            Back()
        )
    }

    fun exit() {
        executeCommands(Back())
    }
}