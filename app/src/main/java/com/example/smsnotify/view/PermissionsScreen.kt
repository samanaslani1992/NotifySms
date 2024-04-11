package com.example.smsnotify.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smsnotify.R
import com.example.smsnotify.ui.theme.SmsNotifyTheme
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

    if (!cameraPermissionState.status.isGranted)
        PermissionsContent(textTitle = textToShow, onSendSmsPermissionClick = {
            cameraPermissionState.launchPermissionRequest()
        })
}

@Composable
fun PermissionsContent(textTitle: String, onSendSmsPermissionClick: () -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {


        Text(textTitle)
        Button(onClick = {
            onSendSmsPermissionClick()
        }) {
            Text(text = stringResource(R.string.request_send_sms_permission))
        }
    }


}

@Composable
@Preview
fun PermissionsPreview() {

    SmsNotifyTheme {
        PermissionsScreen()
    }
}