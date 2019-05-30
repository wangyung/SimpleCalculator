package com.example.simplecalculator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simplecalculator.Calculator
import com.example.simplecalculator.DataBinder

class MainViewModel : ViewModel(), DataBinder {
    val calculator = Calculator(this)

    val displayText: MutableLiveData<String> = MutableLiveData()

    override fun notify(text: String) {
        displayText.value = text
    }
}