package com.example.registrationform

import androidx.compose.ui.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegistrationForm()
        }
    }
}

@Composable
fun RegistrationForm() {
    var name = remember { mutableStateOf("") }
    var email = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }

    var isNameValid = remember { mutableStateOf(false) }
    var isEmailValid = remember { mutableStateOf(false) }
    var isPasswordValid = remember { mutableStateOf(false) }


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)){
        Text(
            "Registration Form",
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        TextField(
            value = name.value,
            onValueChange = {
                newName: String -> name.value = newName
                isNameValid.value= checkName(name.value) },
            label = { Text(text = "Name") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
        )
        if (!isNameValid.value){
            Text(
                text = "Name can't be empty",
                color = Color.Red
            )
        }
        TextField(
            value = email.value,
            onValueChange = {
                newEmail: String -> email.value = newEmail
                isEmailValid.value= checkEmail(email.value)
            },
            label = { Text(text = "Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
        )
        if (!isEmailValid.value){
            Text(
                text = "Email must contain @ and . ",
                color = Color.Red
            )
        }
        TextField(
            value = password.value,
            onValueChange = {
                newPassword: String -> password.value = newPassword
                isPasswordValid.value = checkPassword(password.value)
            },
            label = { Text(text = "Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
        )
        if (!isPasswordValid.value){
            Text(
                text = "Password should be at least 8 symbols",
                color = Color.Red
            )
        }
        Button(
            onClick = {
                password.value=""
                name.value=""
                email.value=""
            },
            enabled = isEmailValid.value && isPasswordValid.value && isNameValid.value,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Submit")
        }
    }
}

fun checkName(name:String):Boolean{
    if (name.length==0){
        return false
    }
    return true
}

fun checkEmail(email:String):Boolean{
    if (!email.contains("@") && !email.contains(".")){
        return false
    }
    return true
}

fun checkPassword(password:String):Boolean{
    if (password.length<8){
        return false
    }
    return true
}