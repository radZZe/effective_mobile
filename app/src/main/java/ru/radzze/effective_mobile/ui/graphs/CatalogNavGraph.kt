package ru.radzze.effective_mobile.ui.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.radzze.effective_mobile.ui.catalog.CatalogScreen
import ru.radzze.effective_mobile.ui.catalog.ProductScreen
import ru.radzze.effective_mobile.ui.main.MainScreen

fun NavGraphBuilder.catalogNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.CATALOG,
        startDestination = CatalogScreen.Catalog.route
    ) {
        composable(route = CatalogScreen.Catalog.route) {
            CatalogScreen()
        }
        composable(route = CatalogScreen.ProductScreen.route) {
            ProductScreen()
        }

    }
}

sealed class CatalogScreen(val route: String) {
    object Catalog : CatalogScreen(route = "CATALOG")
    object ProductScreen : CatalogScreen(route = "PRODUCT")

}