package com.example.smsnotify.presentation.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier


@Composable
fun MyTextField(
    initText: String = "",
    placeholder: String = "",
    singleLine: Boolean = false,
    onValueChange: (String) -> Unit = {},
) {
    var text by remember { mutableStateOf(initText) }

    TextField(modifier = Modifier.fillMaxWidth(),
        singleLine = singleLine,
        minLines = if (singleLine) 1 else 5,
        placeholder = {
            Text(text = placeholder)
        },
        label = {
            Text(text = placeholder)
        },
        value = text,
        onValueChange = {
            text = it
            onValueChange(it)
        })
}
