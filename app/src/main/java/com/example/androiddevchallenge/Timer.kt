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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@ExperimentalAnimationApi
@Composable
fun Countdown(viewModel: MainViewModel) {
    Column {
        AnimatedVisibility(visible = viewModel.mode) {
            Timer(viewModel = viewModel)
        }
        AnimatedVisibility(visible = !viewModel.mode) {
            Input(viewModel = viewModel)
        }
        Row(modifier = Modifier.padding(90.dp, 20.dp)) {
            Button(onClick = { viewModel.onPlayButtonClicked() }) {
                Text("Start", fontSize = 20.sp)
            }
            Button(modifier = Modifier.padding(10.dp, 0.dp), onClick = { viewModel.pause() }) {
                Text("Pause", fontSize = 20.sp)
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
        Card(
            modifier = Modifier
                .padding(20.dp)
                .width(100.dp)
                .height(50.dp),
        ) {
            Text(
                modifier = Modifier.padding(30.dp, 10.dp),
                text = viewModel.minute,
                fontSize = 23.sp
            )
        }

        Card(
            modifier = Modifier
                .padding(20.dp)
                .width(100.dp)
                .height(50.dp),
        ) {
            Text(
                modifier = Modifier.padding(30.dp, 10.dp),
                text = viewModel.second,
                fontSize = 23.sp
            )
        }

        Card(
            modifier = Modifier
                .padding(20.dp)
                .width(100.dp)
                .height(50.dp),
        ) {
            Text(
                modifier = Modifier.padding(30.dp, 10.dp),
                text = viewModel.millis,
                fontSize = 23.sp
            )
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun Input(
    viewModel: MainViewModel
) {
    Row {
        TextField(
            modifier = Modifier
                .padding(20.dp)
                .width(100.dp)
                .height(50.dp),
            value = viewModel.minute,
            onValueChange = { viewModel.minute = it },
            label = { Text(text = "minute") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            modifier = Modifier
                .padding(20.dp)
                .width(100.dp)
                .height(50.dp),
            value = viewModel.second,
            onValueChange = { viewModel.second = it },
            label = { Text(text = "second") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            modifier = Modifier
                .padding(20.dp)
                .width(100.dp)
                .height(50.dp),
            value = viewModel.millis,
            onValueChange = { viewModel.millis = it },
            label = { Text(text = "millisecond") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}
