package com.example.smsnotify.presenter.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier


@Composable
fun MyTextField(
    text: MutableState<String>,
    placeholder: String,
    singleLine: Boolean = false
) {
    TextField(modifier = Modifier.fillMaxWidth(),
        singleLine = singleLine,
        minLines = if (singleLine) 1 else 5,
        placeholder = {
            Text(text = placeholder)
        },
        label = {
            Text(text = placeholder)
        },
        value = text.value,
        onValueChange = {
            text.value = it
        })
}
