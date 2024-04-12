package com.example.smsnotify.presentation.screen.runtimePermission

import android.Manifest.permission.POST_NOTIFICATIONS
import android.Manifest.permission.READ_SMS
import android.Manifest.permission.RECEIVE_SMS
import android.Manifest.permission.SEND_SMS
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smsnotify.R
import com.example.smsnotify.presentation.theme.SmsNotifyTheme
import com.example.smsnotify.presentation.view.ColumnPage
import com.example.smsnotify.presentation.view.MyButton
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@SuppressLint("InlinedApi")
@Composable
fun PermissionsScreen() {

    ColumnPage {
        RuntimePermissionItem(SEND_SMS, "Send SMS")
        RuntimePermissionItem(READ_SMS, "Read SMS")
        RuntimePermissionItem(RECEIVE_SMS, "Receive SMS")
        RuntimePermissionItem(POST_NOTIFICATIONS, "Notification")
    }

}

@Composable
@OptIn(ExperimentalPermissionsApi::class)
private fun RuntimePermissionItem(
    permission: String,
    textTitle: String,
) {
    rememberPermissionState(permission).takeIf { it.status.isGranted.not() }?.let {
        PermissionRequestView(textTitle = textTitle,
            onSendSmsPermissionClick = {
                it.launchPermissionRequest()
            })
    }
}

@Composable
fun PermissionRequestView(textTitle: String, onSendSmsPermissionClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                textTitle,
                Modifier
                    .fillMaxWidth()
                    .weight(1f),
                style = MaterialTheme.typography.titleMedium
            )
            MyButton(
                title = stringResource(R.string.allow), onClick = onSendSmsPermissionClick
            )
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