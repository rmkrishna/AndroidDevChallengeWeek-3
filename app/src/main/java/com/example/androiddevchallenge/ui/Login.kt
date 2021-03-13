package com.example.androiddevchallenge.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

@Composable
fun LoginScreen(isLight: Boolean = MaterialTheme.colors.isLight, screenNavigator: () -> Unit) {
    Surface(
        color = MaterialTheme.colors.background, modifier = Modifier
            .navigationBarsPadding()
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {

            Text(
                text = "Login with email",
                modifier = Modifier
                    .paddingFromBaseline(top = 184.dp)
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.h1.copy(color = MaterialTheme.colors.onPrimary)
            )

            var emailValue by rememberSaveable { mutableStateOf("") }
            var password by rememberSaveable { mutableStateOf("") }

            OutlinedTextField(
                value = emailValue,
                onValueChange = { emailValue = it },
                textStyle = MaterialTheme.typography.body1,
                label = { Text("Email address") },
                placeholder = { Text("Email address") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = MaterialTheme.typography.body1,
                label = { Text("Password") },
                placeholder = { Text("Password (8+ characters)") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            val annotatedText = buildAnnotatedString {
                append("By clicking below, you agree to our ")

                pushStringAnnotation(
                    tag = "URL",
                    annotation = "https://developer.android.com"
                )
                withStyle(
                    SpanStyle(textDecoration = TextDecoration.Underline)
                ) {
                    append("Terms of Use")
                }

                pop()

                append(" and consent to our ")

                pushStringAnnotation(
                    tag = "URL",
                    annotation = "https://developer.android.com"
                )
                withStyle(
                    SpanStyle(textDecoration = TextDecoration.Underline)
                ) {
                    append("Privacy Policy")
                }
            }

            Text(
                text = annotatedText,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .paddingFromBaseline(top = 24.dp, bottom = 16.dp)
                    .wrapContentSize()
                    .align(Alignment.CenterHorizontally),
            )

            Button(
                onClick = screenNavigator,
                modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(percent = 50),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondary
                )
            ) {
                Text(text =  "Log in")
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
private fun LoginScreenPreview() {
    MyTheme {
        LoginScreen(false) {

        }
    }
}

@ExperimentalFoundationApi
@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun LoginDarkPreview() {
    MyTheme(darkTheme = true) {
        LoginScreen(true) {

        }
    }
}

