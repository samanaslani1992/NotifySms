package com.example.smsnotify.presenter.screen.sendSms

import android.content.Context
import android.telephony.SmsManager
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.smsnotify.R
import com.example.smsnotify.presenter.theme.SmsNotifyTheme
import com.example.smsnotify.presenter.view.MyButton
import com.example.smsnotify.presenter.view.MyTextField

@Composable
fun SendSMSFormScreen() {
    val context = LocalContext.current

    val phoneNumber = remember { mutableStateOf("+989123456789") }
    val message = remember { mutableStateOf("Hello World From Android :D") }


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
    phoneNumber: MutableState<String>,
    message: MutableState<String>,
    onSubmitClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        MyTextField(phoneNumber, stringResource(R.string.phone_number), singleLine = true)
        MyTextField(message, stringResource(R.string.message), false)
        MyButton(title = stringResource(R.string.send_sms), onClick = onSubmitClick)

    }
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