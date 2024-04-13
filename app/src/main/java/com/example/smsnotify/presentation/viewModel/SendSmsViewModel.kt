package com.example.smsnotify.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.smsnotify.domain.model.SMS
import com.example.smsnotify.domain.useCase.SmsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SendSmsViewModel @Inject constructor(
    private val smsUseCase: SmsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SMS("+989186565621", "Hello World !"))
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