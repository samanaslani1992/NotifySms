package com.example.smsnotify.presentation.screen.reciveSms

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.smsnotify.R
import com.example.smsnotify.presentation.theme.SmsNotifyTheme
import com.example.smsnotify.presentation.view.ColumnPage
import com.example.smsnotify.presentation.view.MyButton
import com.example.smsnotify.presentation.view.MyTextField

@Composable
fun ReceiveSMSScreen() {

    val context = LocalContext.current


    val phoneNumber = remember { mutableStateOf(context.getString(R.string.fake_phone_number)) }


    ReceiveSMSContent(
        phoneNumber = phoneNumber,
        onSubmitClick = {

        }
    )
}

@Composable
private fun ReceiveSMSContent(
    phoneNumber: MutableState<String>,
    onSubmitClick: () -> Unit = {}
) {
    ColumnPage {

        MyTextField(phoneNumber.value, stringResource(R.string.phone_number), singleLine = true)
        MyButton(title = stringResource(R.string.notify_me_on_this_number), onClick = onSubmitClick)

    }
}


@Composable
@Preview
fun SendSMSFormPreview() {

    SmsNotifyTheme {
        ReceiveSMSScreen()

    }
}