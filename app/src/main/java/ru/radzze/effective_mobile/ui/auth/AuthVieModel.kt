package ru.radzze.effective_mobile.ui.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ru.radzze.effective_mobile.data.UserStore
import ru.radzze.effective_mobile.data.UserStore.PreferenceKeys
import javax.inject.Inject

@HiltViewModel
class AuthVieModel @Inject constructor(
    private val userStore:UserStore
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

    fun onBtnClick() {
        viewModelScope.launch(Dispatchers.IO) {
            userStore.setPref(prefValue = name.value, prefKey = UserStore.name)
            userStore.setPref(prefValue = surname.value, prefKey = UserStore.surname)
            userStore.setPref(prefValue = phone.value, prefKey = UserStore.phone)
            userStore.setPref(true,UserStore.isAuthenticated)
        }

    }

    fun validateCyrillic(text: String): Boolean {
        return text.matches(Regex("[а-яёА-ЯЁ]+"))
    }

}