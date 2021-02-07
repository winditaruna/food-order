package com.food.orderfood.ui.makanan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Selamat datang \n Mau Order Apa?"
    }
    val text: LiveData<String> = _text
}