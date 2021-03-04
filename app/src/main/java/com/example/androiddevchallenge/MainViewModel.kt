/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.CountDownTimer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeUnit.MILLISECONDS

const val TIME_FORMAT: String = "%02d"
const val MILLIS_FORMAT: String = "%03d"

class MainViewModel : ViewModel() {
    var mode: Boolean by mutableStateOf(false)
    var minute: String by mutableStateOf(String.format(TIME_FORMAT, 2))
    var second: String by mutableStateOf(String.format(TIME_FORMAT, 0))
    var millis: String by mutableStateOf(String.format(MILLIS_FORMAT, 0))

    private lateinit var countDownTimer: CountDownTimer

    fun onPlayButtonClicked() {
        val millis = sum(minute.toLong(), second.toLong(), millis.toLong())
        startCountDown(millis)
        mode = true
    }

    fun pause() {
        mode = false
        countDownTimer.cancel()
    }

    private fun startCountDown(time: Long) {
        countDownTimer = object : CountDownTimer(time, 1) {
            override fun onTick(millisUntilFinished: Long) {
                minute = String.format(TIME_FORMAT, toMinute(millisUntilFinished))
                second = String.format(TIME_FORMAT, toSecond(millisUntilFinished))
                millis = String.format(MILLIS_FORMAT, toMillis(millisUntilFinished))
            }

            override fun onFinish() {
                mode = false
            }
        }
        countDownTimer.start()
    }

    private fun toMinute(millis: Long): Long =
        MILLISECONDS.toMinutes(millis)

    private fun toSecond(millis: Long): Long =
        MILLISECONDS.toSeconds(millis) - 60 * toMinute(millis)

    private fun toMillis(millis: Long): Long =
        millis - 1000 * MILLISECONDS.toSeconds(millis)

    private fun sum(minutes: Long, seconds: Long, millis: Long): Long =
        TimeUnit.MINUTES.toMillis(minutes)
            .plus(TimeUnit.SECONDS.toMillis(seconds))
            .plus(millis)
}
