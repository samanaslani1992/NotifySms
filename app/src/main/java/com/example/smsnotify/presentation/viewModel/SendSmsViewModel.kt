package com.example.smsnotify.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.smsnotify.R
import com.example.smsnotify.domain.model.SMS
import com.example.smsnotify.domain.useCase.SmsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SendSmsViewModel @Inject constructor(
    private val smsUseCase: SmsUseCase,
    var context: Application
) : AndroidViewModel(application = context) {

    private val _uiState =
        MutableStateFlow(
            SMS(
                context.getString(R.string.phone_number),
                context.getString(R.string.hello_world)
            )
        )
    val uiState: StateFlow<SMS> = _uiState.asStateFlow()

    fun updatePhoneNumber(phoneNumber: String) {
        _uiState.value = _uiState.value.copy(phoneNumber = phoneNumber)
    }

    fun updateMessage(message: String) {
        _uiState.value = _uiState.value.copy(message = message)
    }


    fun sendSms(
        phoneNumber: String,
        message: String,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        return smsUseCase.sendSMS(SMS(phoneNumber, message), onSuccess, onError)
    }
}