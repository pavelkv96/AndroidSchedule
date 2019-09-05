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
package com.github.pavelkv96.schedule.utils

import com.google.gson.Gson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.reflect.Type
import org.junit.Assert
import retrofit2.Call
import retrofit2.mock.Calls

/**
 * Created by Pavel on 04.09.2019.
 */
object Mocks {

    @JvmStatic
    fun <E> getCall(pNameFile: String, type: Type): Call<E> {
        return Calls.response(getGsonObject(pNameFile, type)) as Call<E>
    }

    private fun <T : Type> getGsonObject(pNameFile: String, type: T): Any {
        return Gson().fromJson(stream(pNameFile), type)
    }

    private fun stream(pName: String): String {
        val classLoader = Mocks::class.java.classLoader
        var resourceStream: InputStream? = null
        if (classLoader != null) {
            resourceStream = classLoader.getResourceAsStream(pName)
        }
        Assert.assertNotNull("resource not found, maybe you forget add .json?", resourceStream)
        return getStringFromInputStream(resourceStream)
    }

    private fun getStringFromInputStream(stream: InputStream?): String {
        var br: BufferedReader? = null
        val sb = StringBuilder()

        var line: String?
        if (stream == null) return ""
        try {
            br = BufferedReader(InputStreamReader(stream))
            line = br.readLine()
            while (line != null) {
                sb.append(line)
                line = br.readLine()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (br != null) {
                try {
                    br.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return sb.toString()
    }
}
