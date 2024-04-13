package com.example.smsnotify.presentation.screen.reciveSms

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smsnotify.R
import com.example.smsnotify.presentation.theme.SmsNotifyTheme
import com.example.smsnotify.presentation.view.ColumnPage
import com.example.smsnotify.presentation.view.MyButton
import com.example.smsnotify.presentation.view.MyTextField
import com.example.smsnotify.presentation.viewModel.ReceiveSmsViewModel

@Composable
fun ReceiveSMSScreen() {

    val receiveSmsViewModel = hiltViewModel<ReceiveSmsViewModel>()
    val phoneNumberState = receiveSmsViewModel.phoneNumberState.collectAsState()

    ReceiveSMSContent(
        phoneNumber = phoneNumberState,
        onPhoneNumberChange = {
            receiveSmsViewModel.updatePhoneNumber(it)
        },
        onSubmitClick = {
            receiveSmsViewModel.savePhoneNumber(phoneNumberState.value)
        }
    )
}

@Composable
private fun ReceiveSMSContent(
    phoneNumber: State<String>,
    onPhoneNumberChange: (String) -> Unit,
    onSubmitClick: () -> Unit
) {
    ColumnPage {

        MyTextField(
            phoneNumber.value,
            stringResource(R.string.phone_number),
            singleLine = true,
            onValueChange = onPhoneNumberChange
        )
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