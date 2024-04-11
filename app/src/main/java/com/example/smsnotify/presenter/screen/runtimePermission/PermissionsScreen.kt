package com.example.smsnotify.presenter.screen.runtimePermission

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.smsnotify.R
import com.example.smsnotify.presenter.theme.SmsNotifyTheme
import com.example.smsnotify.presenter.view.ColumnPage
import com.example.smsnotify.presenter.view.MyButton
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionsScreen() {

    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.SEND_SMS
    )
    val textToShow = if (cameraPermissionState.status.shouldShowRationale) {
        stringResource(R.string.send_sms_permission)
    } else {
        stringResource(R.string.send_sms_permission_required)
    }

    if (!cameraPermissionState.status.isGranted) PermissionsContent(
        textTitle = textToShow,
        onSendSmsPermissionClick = {
            cameraPermissionState.launchPermissionRequest()
        })
}

@Composable
fun PermissionsContent(textTitle: String, onSendSmsPermissionClick: () -> Unit) {
    ColumnPage {


        Text(textTitle)

        MyButton(
            title = stringResource(R.string.request_send_sms_permission),
            onClick = onSendSmsPermissionClick
        )

    }


}

@Composable
@Preview
fun PermissionsPreview() {

    SmsNotifyTheme {
        PermissionsScreen()
    }
}