package com.example.smsnotify.presenter.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SendSmsViewModel : ViewModel() {

    data class SendSmsState(
        val phoneNumber: String = "",
        val message: String = "",
    )

    private val _formData = MutableLiveData(SendSmsState("", ""))
    val formData: LiveData<SendSmsState> = _formData

    fun updatePhoneNumber(phoneNumber: String) {
        _formData.value = _formData.value?.copy(phoneNumber = phoneNumber)
    }

    fun updateMessage(message: String) {
        _formData.value = _formData.value?.copy(message = message)
    }

    fun validateForm(): Boolean {
        return formData.value?.phoneNumber?.isNotEmpty() == true &&
                formData.value?.message?.isNotEmpty() == true

    }
}