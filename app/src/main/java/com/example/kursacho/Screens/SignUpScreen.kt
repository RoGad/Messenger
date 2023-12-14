package com.example.kursacho.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kursacho.CheckSignedIn
import com.example.kursacho.CommonProgressBar
import com.example.kursacho.DestinationScreen
import com.example.kursacho.LCViewModel
import com.example.kursacho.R
import com.example.kursacho.navigateTo

//починил ы :)
//взято из fun SignUpScreen, решить проблему, добавить по возможности
//vm: LCViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavController, vm: LCViewModel
) {
    CheckSignedIn(vm = vm, navController = navController)

    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight()
            .verticalScroll(
                rememberScrollState()
            ),
            horizontalAlignment = Alignment.CenterHorizontally
            
        ){
            val nameState = remember{
                mutableStateOf(TextFieldValue())
            }
            val numberState = remember{
                mutableStateOf(TextFieldValue())
            }
            val emailState = remember{
                mutableStateOf(TextFieldValue())
            }
            val passwordState = remember{
                mutableStateOf(TextFieldValue())
            }

            val focus = LocalFocusManager.current
            
            Text(text = "Регистрация",
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(24.dp)
            )
            OutlinedTextField(
                value = nameState.value,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(R.color.light_green),
                    unfocusedBorderColor = colorResource(R.color.light_gray)
                ),
                onValueChange = {
                nameState.value=it
            },
                modifier = Modifier
                    .padding(7.dp)
                    .size(width = 300.dp, height = 50.dp),
                shape = RoundedCornerShape(10.dp),
                placeholder = {
                    Text(text = "Логин", color = colorResource(R.color.light_gray))
                }

            )
            OutlinedTextField(
                value = numberState.value,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(R.color.light_green),
                    unfocusedBorderColor = colorResource(R.color.light_gray)
                ),
                onValueChange = {
                    numberState.value=it
                },
                modifier = Modifier
                    .padding(7.dp)
                    .size(width = 300.dp, height = 50.dp),
                shape = RoundedCornerShape(10.dp),
                placeholder = {
                    Text(text = "Телефон", color = colorResource(R.color.light_gray))
                }

            )
            OutlinedTextField(
                value = emailState.value,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(R.color.light_green),
                    unfocusedBorderColor = colorResource(R.color.light_gray)
                ),
                onValueChange = {
                    emailState.value=it
                },
                modifier = Modifier
                    .padding(7.dp)
                    .size(width = 300.dp, height = 50.dp),
                shape = RoundedCornerShape(10.dp),
                placeholder = {
                    Text(text = "Email", color = colorResource(R.color.light_gray))
                }

            )
            OutlinedTextField(
                value = passwordState.value,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(R.color.light_green),
                    unfocusedBorderColor = colorResource(R.color.light_gray)
                ),
                onValueChange = {
                    passwordState.value=it
                },
                modifier = Modifier
                    .padding(7.dp)
                    .size(width = 300.dp, height = 50.dp),
                shape = RoundedCornerShape(10.dp),
                placeholder = {
                    Text(text = "Пароль", color = colorResource(R.color.light_gray))
                }

            )
            Button(onClick = {vm.signUp(
                name = nameState.value.text,
                number = numberState.value.text,
                email = emailState.value.text,
                password = passwordState.value.text,
            ) },
                modifier = Modifier
                    .padding(8.dp)
                    .size(width = 300.dp, height = 50.dp),
                colors = ButtonDefaults.buttonColors(colorResource(R.color.light_green))) {
                Text(text = "Зарегистрироваться",
                     style = TextStyle(
                        fontSize = 16.sp,fontWeight = FontWeight(600)
                    ))
            }
            Text(text = "У меня уже есть аккаунт",
                color = colorResource(R.color.light_green),
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        navigateTo(navController, DestinationScreen.Login.route)
                    })
        }
    }
    if(vm.inProcess.value){
        CommonProgressBar()
    }
}


