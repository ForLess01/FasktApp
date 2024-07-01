package com.alfontetarqui.fasktapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> get() = _userName

    private val _providerType = MutableLiveData<ProviderType>()
    val providerType: LiveData<ProviderType> get() = _providerType

    fun setUserInfo(name: String, provider: ProviderType) {
        _userName.value = name
        _providerType.value = provider
    }
}
