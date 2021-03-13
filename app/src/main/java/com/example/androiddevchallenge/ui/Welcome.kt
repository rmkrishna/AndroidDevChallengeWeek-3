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
package com.example.androiddevchallenge.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.pink900

@Composable
fun WelcomeScreen(isLight: Boolean = MaterialTheme.colors.isLight, screenNavigator: () -> Unit) {
    Surface(color = MaterialTheme.colors.primary) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painterResource(id = if (isLight) R.drawable.welcome_bg else R.drawable.welcome_bg_dark),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )

            WelcomeContent(modifier = Modifier.padding(top = 72.dp), screenNavigator)
        }
    }
}

@Composable
fun WelcomeContent(
    modifier: Modifier,
    screenNavigator: () -> Unit,
    isLight: Boolean = MaterialTheme.colors.isLight
) {

    Column(modifier = modifier.fillMaxWidth()) {
        Image(
            painterResource(id = if (isLight) R.drawable.ic_welcome else R.drawable.ic_welcome_dark),
            contentDescription = null,
            modifier = Modifier
                .wrapContentSize()
                .offset(x = 88.dp)
        )

        Image(
            painterResource(id = if (isLight) R.drawable.ic_logo else R.drawable.ic_logo_dark),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 48.dp)
                .wrapContentSize()
                .align(Alignment.CenterHorizontally)

        )

        Text(
            text = stringResource(R.string.WELCOME_SCREEN_description),
            modifier = Modifier
                .paddingFromBaseline(top = 32.dp, bottom = 40.dp)
                .wrapContentSize()
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.subtitle1.copy(color = MaterialTheme.colors.onPrimary)
        )

        val buttonModifier = Modifier
            .fillMaxWidth()
            .requiredHeight(48.dp)

        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.secondary
            ),
            shape = RoundedCornerShape(percent = 50),
            onClick = { },
            modifier = buttonModifier.padding(horizontal = 16.dp)
        ) {
            Text(stringResource(R.string.COMMON_create_account), style = TextStyle(color = MaterialTheme.colors.onSecondary))
        }

        TextButton(
            shape = RoundedCornerShape(percent = 50),
            onClick = screenNavigator,
            modifier = buttonModifier.padding(start = 16.dp, top = 8.dp, end = 16.dp)
        ) {
            Text(stringResource(R.string.COMMON_login), style = TextStyle(color = if (isLight) pink900 else Color.White))
        }
    }
}

@ExperimentalFoundationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
private fun WelcomeScreenPreview() {
    MyTheme {
        WelcomeScreen(false) {
        }
    }
}

@ExperimentalFoundationApi
@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        WelcomeScreen(true) {
        }
    }
}
