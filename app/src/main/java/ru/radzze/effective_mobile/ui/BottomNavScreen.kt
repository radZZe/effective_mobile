package ru.radzze.effective_mobile.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import ru.radzze.effective_mobile.R

sealed class BottomNavScreen(
    val route: String,
    val title: String,
    val icon: Int){

    object Main : BottomNavScreen(
        route = "MAIN",
        title = "Главная",
        icon = R.drawable.main_nav
    )
    object Catalog : BottomNavScreen(
        route = "CATALOG",
        title = "Каталог",
        icon = R.drawable.catalog_nav
    )
    object Cart : BottomNavScreen(
        route = "CART",
        title = "Корзина",
        icon = R.drawable.cart_nav
    )
    object Stock : BottomNavScreen(
        route = "STOCK",
        title = "Акции",
        icon = R.drawable.stock_nav
    )
    object Account : BottomNavScreen(
        route = "ACCOUNT",
        title = "Профиль",
        icon = R.drawable.account_nav
    )
}
