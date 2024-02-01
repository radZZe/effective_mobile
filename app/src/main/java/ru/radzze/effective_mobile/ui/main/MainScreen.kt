package ru.radzze.effective_mobile.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.radzze.effective_mobile.ui.topAppBar

@Composable
fun MainScreen(){
    Column(modifier=Modifier.fillMaxSize()) {
        topAppBar(title = "Главная")

    }
}

