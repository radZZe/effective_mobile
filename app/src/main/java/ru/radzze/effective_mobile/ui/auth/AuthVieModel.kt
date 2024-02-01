package ru.radzze.effective_mobile.ui.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthVieModel @Inject constructor(
) : ViewModel() {
    val name = mutableStateOf("")
    val surname = mutableStateOf("")
    val phone = mutableStateOf("")
    val isNameFieldError = mutableStateOf(false)
    val isSurnameFieldError = mutableStateOf(false)
    val isPhoneFieldError = mutableStateOf(false)


    fun nameValueChanged(text: String) {
        isNameFieldError.value = !validateCyrillic(text)
        name.value = text
    }

    fun surnameValueChanged(text: String) {
        isSurnameFieldError.value = !validateCyrillic(text)
        surname.value = text
    }

    fun phoneValueChanged(text: String) {
        isPhoneFieldError.value = text.length != 10
        if(text == "7") return
        if(text.length < 11){
            phone.value = text
        }
    }

    fun onBtnCluck() {
        TODO()
    }

    fun validateCyrillic(text: String): Boolean {
        return text.matches(Regex("[а-яёА-ЯЁ]+"))
    }

}