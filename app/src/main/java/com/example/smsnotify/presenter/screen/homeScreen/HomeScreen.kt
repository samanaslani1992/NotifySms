package com.example.smsnotify.presenter.screen.homeScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smsnotify.presenter.screen.reciveSms.ReceiveSMSScreen
import com.example.smsnotify.presenter.screen.runtimePermission.PermissionsScreen
import com.example.smsnotify.presenter.screen.sendSms.SendSMSScreen
import com.example.smsnotify.presenter.theme.SmsNotifyTheme
import com.example.smsnotify.presenter.view.ColumnPage
import com.example.smsnotify.presenter.view.MyTopAppBar

@Composable
fun HomeScreen() {
    SmsNotifyTheme {
        Scaffold(
            topBar = {
                MyTopAppBar()
            }
        ) {

            ColumnPage(
                modifier = Modifier
                    .padding(it)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 32.dp),
            ) {
                PermissionsScreen()

                SendSMSScreen()


                ReceiveSMSScreen()
            }
        }

    }
    }