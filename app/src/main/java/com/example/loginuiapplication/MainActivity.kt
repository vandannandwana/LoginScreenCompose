package com.example.loginuiapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginuiapplication.ui.theme.LoginUIApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginUIApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginUI()
                }
            }
        }
    }
}

@Composable
private fun LoginUI() {
    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.login_background), contentDescription = "login_background", contentScale = ContentScale.Crop, modifier = Modifier
            .fillMaxSize()
            .align(Alignment.Center))
        Image(painter = painterResource(id = R.drawable.img), contentDescription = "logo", modifier = Modifier
            .align(Alignment.TopCenter)
            .padding(40.dp)
            .size(120.dp))

        var clicked by rememberSaveable {
            mutableStateOf(false)
        }
        val buttonAlignment = if (clicked) Alignment.BottomEnd else Alignment.Center

        val extraPadding by animateDpAsState(
            if (clicked) 800.dp else 0.dp,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessHigh
            ), label = ""
        )

        LoginWindow(extraPadding,buttonAlignment,onClick={clicked = !clicked})

    }
}

@Composable
private fun LoginWindow(extrapadding: Dp,buttonAlignment: Alignment, onClick:() -> Unit) {

    var email by rememberSaveable {
        mutableStateOf("")
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }
    Box(modifier = Modifier.fillMaxSize()){
        Box(modifier = Modifier
            .size(extrapadding)
            .align(Alignment.Center)
            .drawBehind {
                this.drawCircle(color = Color(0x1DFFFFFF))
            }) {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                OutlinedTextField(
                    value = email,
                    onValueChange = {email = it},
                    label = {Text(text = "Enter Email")},
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color(0xFF878888),
                        focusedIndicatorColor = Color.Black,
                        unfocusedIndicatorColor = Color.Black))
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = "Enter Password") },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color(0xFF878888),
                        focusedIndicatorColor = Color.Black,
                        unfocusedIndicatorColor = Color.Black))
                Spacer(modifier = Modifier.height(12.dp))

            }


        }
        Button(
            onClick = { onClick() },
            modifier = Modifier
                .align(buttonAlignment)
                .padding(30.dp),
            colors = ButtonDefaults.buttonColors(Color(0xEB2D2A23)))
        {
            Text(text = "Login", color = Color(0xB0E7071F), fontSize = 28.sp)
        }
    }

}