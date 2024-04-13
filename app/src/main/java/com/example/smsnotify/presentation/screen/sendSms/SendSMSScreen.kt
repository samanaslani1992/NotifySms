package com.example.smsnotify.presentation.screen.sendSms

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smsnotify.R
import com.example.smsnotify.domain.model.SMS
import com.example.smsnotify.presentation.theme.SmsNotifyTheme
import com.example.smsnotify.presentation.view.ColumnPage
import com.example.smsnotify.presentation.view.MyButton
import com.example.smsnotify.presentation.view.MyTextField
import com.example.smsnotify.presentation.viewModel.SendSmsViewModel

@Composable
fun SendSMSScreen() {
    val context = LocalContext.current


    val sendSmsViewModel = hiltViewModel<SendSmsViewModel>()
    val sendSmsUiState = sendSmsViewModel.uiState.collectAsState()

    SendSMSContent(
        uiState = sendSmsUiState,
        onPhoneNumberChange = {
            sendSmsViewModel.updatePhoneNumber(it)
        },
        onMessageChange = { sendSmsViewModel.updateMessage(it) },
        onSubmitClick = {
            sendSmsViewModel.sendSms(
                phoneNumber = sendSmsUiState.value.phoneNumber,
                message = sendSmsUiState.value.message,
                onSuccess = {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                },
                onError = {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            )
        })
}

@Composable
private fun SendSMSContent(
    uiState: State<SMS>,
    onPhoneNumberChange: (phoneNumber: String) -> Unit,
    onMessageChange: (message: String) -> Unit,
    onSubmitClick: () -> Unit
) {
    ColumnPage {

        MyTextField(
            initText = uiState.value.phoneNumber,
            placeholder = stringResource(R.string.phone_number),
            singleLine = true,
            onValueChange = onPhoneNumberChange
        )
        MyTextField(
            initText = uiState.value.message,
            placeholder = stringResource(R.string.message),
            onValueChange = onMessageChange
        )
        MyButton(
            title = stringResource(R.string.send_sms),
            onClick = onSubmitClick
        )

    }
}


@Composable
@Preview
fun SendSMSFormPreview() {

    SmsNotifyTheme {
        SendSMSScreen()
    }
}