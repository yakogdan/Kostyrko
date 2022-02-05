package com.yakogdan.tinkofflab.ui.hot

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HotViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is hot Fragment"
    }
    val text: LiveData<String> = _text
}