package com.example.smsnotify.presenter.view

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.smsnotify.R

@Composable
@Preview
fun MyButton(title: String = stringResource(R.string.send_sms), onClick: () -> Unit = {}) {

    Button(onClick = onClick) {
        Text(text =title)
    }

}