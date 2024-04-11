package com.example.smsnotify.presenter.view

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smsnotify.R

@Composable
@Preview
fun MyButton(title: String = stringResource(R.string.send_sms), onClick: () -> Unit = {}) {

    Button(
        modifier = Modifier
            .defaultMinSize(minWidth = 180.dp),
        onClick = onClick,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 4.dp),
            text = title,
            style = MaterialTheme.typography.titleMedium
        )
    }

}