package com.example.smsnotify.view

import android.content.Context
import android.telephony.SmsManager
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smsnotify.R
import com.example.smsnotify.ui.theme.SmsNotifyTheme
import com.example.smsnotify.viewModel.SendSmsViewModel

@Composable
fun SendSMSFormScreen() {
    val context = LocalContext.current

    val phoneNumber = remember { mutableStateOf("+989123456789") }
    val message = remember { mutableStateOf("This is a test") }


    SendSMSFormContent(phoneNumber, message, onSubmitClick = {
        sendSMS(context = context,
            phoneNumber = phoneNumber.value,
            message = message.value,
            success = {

                phoneNumber.value = ""
                message.value = ""
                Toast.makeText(context, context.getString(R.string.sms_sent), Toast.LENGTH_SHORT)
                    .show()
            },
            error = {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            })
    })
}

@Composable
private fun SendSMSFormContent(
    phoneNumber: MutableState<String>, message: MutableState<String>, onSubmitClick: () -> Unit = {}
) {

   // val viewModel: SendSmsViewModel = viewModel()

    Column(
        Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {


        MyTextField(phoneNumber, stringResource(R.string.phone_number), singleLine = true)
        MyTextField(message, stringResource(R.string.message), false)

        Button(onClick = onSubmitClick) {
            Text(text = stringResource(R.string.send_sms))
        }

    }
}

@Composable
private fun MyTextField(
    text: MutableState<String>, placeholder: String, singleLine: Boolean = false
) {
    TextField(modifier = Modifier.fillMaxWidth(),
        singleLine = singleLine,
        minLines = if (singleLine) 1 else 5,
        placeholder = {
            Text(text = placeholder)
        },
        value = text.value,
        onValueChange = {
            text.value = it
        })
}

private fun sendSMS(
    context: Context,
    phoneNumber: String,
    message: String,
    success: () -> Unit,
    error: (String) -> Unit
) {
    try {
        val smsManager = context.getSystemService(SmsManager::class.java)

        smsManager.sendTextMessage(phoneNumber, null, message, null, null)
        success()
    } catch (e: Exception) {
        error(e.message ?: context.getString(R.string.error_sending_sms))
    }

}

@Composable
@Preview
fun SendSMSFormPreview() {

    SmsNotifyTheme {
        SendSMSFormScreen()

    }
}