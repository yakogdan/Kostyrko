package com.yakogdan.tinkofflab.ui.best

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BestViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is best Fragment"
    }
    val text: LiveData<String> = _text
}