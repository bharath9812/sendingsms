package com.example.sendsms

import android.content.Context
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sendsms.ui.theme.SendsmsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SendsmsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    smsUI(context = LocalContext.current)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun smsUI(context: Context) {
    // on below line creating variable for
    // service status and button value.
    val nivb = remember {
        mutableStateOf("")
    }
    TextField(value = nivb.value, onValueChange ={nivb.value=it} )
    val phoneNumber = remember {
        mutableStateOf("")
    }
    val message = remember {
        mutableStateOf("")
    }

    // on below line we are creating a column,
    Column(
        // on below line we are adding a modifier to it,
        modifier = Modifier
            .fillMaxSize()
            // on below line we are adding a padding.
            .padding(all = 30.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // on below line we are adding a text for heading.
        Text(
            // on below line we are specifying text
            text = "SMS sending man",
            // on below line we are specifying text color,
            // font size and font weight
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        // on below line we are creating a text field for our phone number.
        TextField(
            // on below line we are specifying value for our email text field.
            value = phoneNumber.value,
            // on below line we are adding on value change for text field.
            onValueChange = { phoneNumber.value = it },
            // on below line we are adding place holder as text
            // as "Enter your email"
            placeholder = { Text(text = "Enter your phone number") },
            // on below line we are adding modifier to it
            // and adding padding to it and filling max width
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            // on below line we are adding text style
            // specifying color and font size to it.
            //textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            // on below line we ar adding single line to it.
            singleLine = true,
        )
        // on below line we are adding a spacer.
        Spacer(modifier = Modifier.height(20.dp))

        // on below line we are creating a text field for our message number.
        TextField(
            // on below line we are specifying value for our message text field.
            value = message.value,
            // on below line we are adding on value change for text field.
            onValueChange = { message.value = it },
            // on below line we are adding place holder as text as "Enter your email"
            placeholder = { Text(text = "Enter your message") },
            // on below line we are adding modifier to it
            // and adding padding to it and filling max width
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            // on below line we are adding text style
            // specifying color and font size to it.
            //textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            // on below line we are adding single line to it.
            singleLine = true,
        )
        // on below line adding a spacer.
        Spacer(modifier = Modifier.height(20.dp))
        // on below line adding a button to send SMS
        Button(onClick = {
            // on below line running a try catch block for sending sms.
            try {
                // on below line initializing sms manager.
                val smsManager: SmsManager = SmsManager.getDefault()
                // on below line sending sms
                smsManager.sendTextMessage(phoneNumber.value, null, message.value, null, null)
                // on below line displaying
                // toast message as sms send.
                Toast.makeText(
                    context,
                    "Message Sent",
                    Toast.LENGTH_LONG
                ).show()
            } catch (e: Exception) {
                // on below line handling error and
                // displaying toast message.
                Toast.makeText(
                    context,
                    "Error : " + e.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        }) {
            // on below line creating a text for our button.
            Text(
                // on below line adding a text ,
                // padding, color and font size.
                text = "Send SMS",
                modifier = Modifier.padding(10.dp),
                color = Color.White,
                fontSize = 15.sp
            )
        }
    }
}