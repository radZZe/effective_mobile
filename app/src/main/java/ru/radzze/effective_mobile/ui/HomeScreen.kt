package ru.radzze.effective_mobile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.radzze.effective_mobile.ui.graphs.MainNavGraph
import ru.radzze.effective_mobile.ui.theme.Purple40
import ru.radzze.effective_mobile.ui.theme.sf_pro

@Composable
fun HomeScreen(navController: NavHostController = rememberNavController(),isAuth:Boolean) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) { it->
        MainNavGraph(navController = navController,isAuth)

    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomNavScreen.Main,
        BottomNavScreen.Catalog,
        BottomNavScreen.Cart,
        BottomNavScreen.Stock,
        BottomNavScreen.Account,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomNavigation {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomNavScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    }
    BottomNavigationItem(
        modifier = Modifier.background(Color.White),
        label = {
            BottomText(screen.title,selected)
        },
        icon = {
            Icon(painterResource(id = screen.icon), contentDescription = "Navigation Icon",tint = if(selected == true ) Purple40 else Color(62,62,62))
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        selectedContentColor = Color.Red,
        unselectedContentColor = Color(62,62,62),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}

@Composable
fun BottomText(
    text:String,
    isSelected:Boolean?,
){
    Text(text = text, fontFamily = sf_pro, fontSize = 10.sp, lineHeight = 11.sp, maxLines = 1, color = if(isSelected == true) Purple40 else Color(62,62,62))
}