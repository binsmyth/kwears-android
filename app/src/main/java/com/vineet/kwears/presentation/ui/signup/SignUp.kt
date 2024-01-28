package com.vineet.kwears.presentation.ui.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview

val viewModel = SignUpViewModel()
@Preview(showSystemUi = true)
@Composable
fun SignUp(){
    MaterialTheme() {
        Column() {
            //Email
            TextField(
                value = viewModel.email,
                label = { Text("Email") },
                onValueChange = {}
            )
            //First Name
            TextField(
                value = viewModel.firstName,
                label = { Text("First Name") },
                onValueChange = {}
            )
            //Last Name
            TextField(
                value = viewModel.lastName,
                label = { Text("Last Name") },
                onValueChange = {}
            )
            //Username
            TextField(
                value = viewModel.userName,
                label = { Text("Username") },
                onValueChange = {}
            )
            //Password
            TextField(
                value = viewModel.password,
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = {}
            )
            Button(onClick={}){
                Text("Sign Up")
            }
        }
    }
}