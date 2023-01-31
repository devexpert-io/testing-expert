package com.devexperto.testingexpert.composeui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.devexperto.testingexpert.composeui.navigation.NavItem
import com.devexperto.testingexpert.composeui.navigation.Navigation
import com.devexperto.testingexpert.composeui.navigation.TopBar
import com.devexperto.testingexpert.composeui.settings.SettingsViewModel
import com.devexperto.testingexpert.composeui.settings.Theme

@Composable
fun Home(vm: SettingsViewModel = hiltViewModel()) {
    val theme by vm.theme.collectAsState(initial = Theme.SAME_AS_SYSTEM)
    Home(theme)
}

@Composable
fun Home(theme: Theme) {
Screen(theme) {    val navController = rememberNavController()

    Scaffold(
        topBar = { TopBar() },
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            Navigation(navController)
        }
    }
}

}

@Composable
fun BottomNavBar(navController: NavHostController) {
    val current by navController.currentBackStackEntryAsState()
    BottomNavigation {
        NavItem.entries.forEach { navItem ->
            val title = stringResource(id = navItem.title)
            BottomNavigationItem(
                selected = current?.destination?.hasRoute(navItem.route::class) == true,
                onClick = { navController.navigateToNavItem(navItem) },
                icon = { Icon(navItem.icon, contentDescription = title) },
                label = { Text(text = title) }
            )
        }
    }
}

private fun NavHostController.navigateToNavItem(navItem: NavItem) {
    navigate(navItem.route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }
}