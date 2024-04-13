package com.example.smsnotify.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.smsnotify.domain.useCase.SmsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ReceiveSmsViewModel @Inject constructor(
    private val smsUseCase: SmsUseCase
) : ViewModel() {

    private val _phoneNumberState = MutableStateFlow(smsUseCase.getPhoneNumber())
    val phoneNumberState: StateFlow<String> = _phoneNumberState.asStateFlow()

    fun updatePhoneNumber(phoneNumber: String) {
        _phoneNumberState.value = phoneNumber
    }

    fun savePhoneNumber(phoneNumber: String) {
        return smsUseCase.savePhoneNumber(phoneNumber)
    }
}