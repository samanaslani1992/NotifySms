package com.example.smsnotify.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.smsnotify.ui.theme.SmsNotifyTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionsView() {

    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.SEND_SMS
    )

    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (!cameraPermissionState.status.isGranted) {
            val textToShow = if (cameraPermissionState.status.shouldShowRationale) {
                "The send SMS permission is important for this app. Please grant the permission."
            } else {
                "Send SMS permission required for this feature to be available. " +
                        "Please grant the permission"
            }
            Text(textToShow)
            Button(onClick = {
                cameraPermissionState.launchPermissionRequest()
            }) {

                Text(text = "Request send SMS permission")

            }
        }


    }
}

@Composable
@Preview
fun PermissionsPreview() {

    SmsNotifyTheme {
        PermissionsView()
    }
}