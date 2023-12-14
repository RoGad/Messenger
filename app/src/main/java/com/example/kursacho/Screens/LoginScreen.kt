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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(vm: LCViewModel, navController: NavController) {
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
            val emailState = remember{
                mutableStateOf(TextFieldValue())
            }
            val passwordState = remember{
                mutableStateOf(TextFieldValue())
            }

            val focus = LocalFocusManager.current

            Text(text = "Вход",
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(24.dp)
            )
            OutlinedTextField(
                value = emailState.value,
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
            Button(onClick = {
                             vm.loginIn(emailState.value.text,passwordState.value.text)
            },
                modifier = Modifier
                    .padding(8.dp)
                    .size(width = 300.dp, height = 50.dp),
                colors = ButtonDefaults.buttonColors(colorResource(R.color.light_green))) {
                Text(text = "Войти",
                    style = TextStyle(
                        fontSize = 16.sp,fontWeight = FontWeight(600)
                    )
                )
            }
            Text(text = "Создать новый аккаунт",
                color = colorResource(R.color.light_green),
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        navigateTo(navController, DestinationScreen.SignUp.route)
                    })
        }
    }
    if(vm.inProcess.value){
        CommonProgressBar()
    }
}