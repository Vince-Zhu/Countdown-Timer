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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@ExperimentalAnimationApi
@Composable
fun Countdown(viewModel: MainViewModel) {
    Column() {
        AnimatedVisibility(visible = viewModel.mode) {
            Timer(viewModel = viewModel)
        }
        AnimatedVisibility(visible = !viewModel.mode) {
            Input(viewModel = viewModel)
        }
        Row() {
            Button(onClick = { viewModel.onPlayButtonClicked() }) {
                Text("Play")
            }
            Button(onClick = { viewModel.pause() }) {
                Text("Pause")
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun Timer(
    viewModel: MainViewModel
) {
    Row {
        Text(text = viewModel.minute)
        Text(text = viewModel.second)
        Text(text = viewModel.millis)
    }
}

@ExperimentalAnimationApi
@Composable
fun Input(
    viewModel: MainViewModel
) {
    Row {
        TextField(value = viewModel.minute, onValueChange = { viewModel.minute = it })
        TextField(value = viewModel.second, onValueChange = { viewModel.second = it })
        TextField(value = viewModel.millis, onValueChange = { viewModel.millis = it })
    }
}
