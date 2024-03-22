/*
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.haile.nationalparks.compose

import LegoScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import me.haile.nationalparks.compose.favorites.FavoritesScreen
import me.haile.nationalparks.compose.gallery.GalleryScreen
import me.haile.nationalparks.compose.home.HomeScreen
import me.haile.nationalparks.compose.park.ParkScreen
import me.haile.nationalparks.compose.plan.PlanScreen
import me.haile.nationalparks.compose.thingstodo.ThingsTodoScreen

@Composable
fun NationalParksApp() {
    val navController = rememberNavController()
    NationalParksNavHost(
        navController = navController,
        titleState = remember { mutableStateOf("National Parks") }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NationalParksNavHost(
    navController: NavHostController,
    titleState: MutableState<String>
) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(titleState.value) }, actions = {
            IconButton(onClick = {
                titleState.value = Screen.Home.title
                if (navController.currentBackStackEntry?.destination?.route != Screen.Lego.route)
                    navController.navigate(Screen.Lego.route) {
                        launchSingleTop = true
                        restoreState = true
                    }
//                navController.navigate(Screen.Home.route) {
//                    launchSingleTop = true
//                    restoreState = true
//                }
            }) {
                Icon(Icons.Filled.Favorite, contentDescription = "Lego")
            }
        })
    }, bottomBar = {
        BottomAppBar {
            IconButton(
                onClick = { /* Handle navigation icon click */
                    titleState.value = Screen.Home.title
                    if (navController.currentBackStackEntry?.destination?.route != Screen.Home.route)
                        navController.navigate(Screen.Home.route) {
                            launchSingleTop = true
                            restoreState = true
                        }
//                    navController.navigate(Screen.Home.route) {
//                        launchSingleTop = true
//                        restoreState = true
//                    }
                }) {
                Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
            }

            IconButton(onClick = { /* Handle navigation icon click */
                titleState.value = Screen.Favorites.title
                if (navController.currentBackStackEntry?.destination?.route != Screen.Favorites.route)
                    navController.navigate(Screen.Favorites.route) {
                        launchSingleTop = true
                        restoreState = true
                    }
            }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }

            IconButton(onClick = { /* Handle navigation icon click */
                titleState.value = Screen.Favorites.title
                if (navController.currentBackStackEntry?.destination?.route != Screen.Favorites.route)
                    navController.navigate(Screen.Favorites.route) {
                        launchSingleTop = true
                        restoreState = true
                    }
            }) {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Profile")
            }
            // Add more BottomNavigationItem as needed
        }
    }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Screen.Home.route) {
                HomeScreen(onParkClick = {
                    navController.navigate(
                        Screen.Park.createRoute(
                            parkId = it.parkCode,
                            parkTitle = it.fullName,
                        )
                    )
                })
            }

            composable(
                route = Screen.Park.route, arguments = Screen.Park.navArguments
            ) {
                ParkScreen(onBackClick = { navController.navigateUp() }, onFabClick = {
                    navController.navigateUp()
                }, onGoToGalleryClick = {
                    navController.navigate(
                        Screen.Gallery.createRoute(it)
                    )
                }, onGoToThingsToDoClick = {
                    navController.navigate(
                        Screen.ThingsToDo.createRoute(it)
                    )
                }, onPlanPhotographyTripClick = {
                    navController.navigate(
                        Screen.Plan.createRoute(it)
                    )
                })
            }

            composable(
                route = Screen.ThingsToDo.route, arguments = Screen.ThingsToDo.navArguments
            ) {
                ThingsTodoScreen(
//                onBackClick = { navController.navigateUp() },
//                onFabClick = {
//                    navController.navigateUp()
//                }
                )
            }
            composable(
                route = Screen.Gallery.route, arguments = Screen.Gallery.navArguments
            ) {
                GalleryScreen()
//                onPhotoClick = {
//                    val uri = Uri.parse(it.user.attributionUrl)
//                    val intent = Intent(Intent.ACTION_VIEW, uri)
//                    activity.startActivity(intent)
//                },
//                onUpClick = {
//                    navController.navigateUp()
//                })
            }

            composable(
                route = Screen.Plan.route, arguments = Screen.Plan.navArguments
            ) {
                PlanScreen()
//                onPhotoClick = {
//                    val uri = Uri.parse(it.user.attributionUrl)
//                    val intent = Intent(Intent.ACTION_VIEW, uri)
//                    activity.startActivity(intent)
//                },
//                onUpClick = {
//                    navController.navigateUp()
//                })
            }

            composable(
                route = Screen.Favorites.route, arguments = listOf()
            ) {
                FavoritesScreen(
//                onBackClick = { navController.navigateUp() },
//                onFabClick = {
//                    navController.navigateUp()
//                }
                )
            }

            composable(route = Screen.Lego.route) {
                LegoScreen()
            }
        }
    }
}

fun handleClick() {
    // Handle click


}

@Composable
fun SnackBar(text: String) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }, floatingActionButton = {
        var clickCount by remember { mutableStateOf(0) }
        ExtendedFloatingActionButton(onClick = {
            // show snackbar as a suspend function
            scope.launch {
                snackbarHostState.showSnackbar(
                    "Snackbar -- $text -- # ${++clickCount}"
                )
            }
        }) { Text("Show snackbar") }
    }, content = { innerPadding ->
        Text(
            text = "Body content",
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .wrapContentSize()
        )
    })
}


// Helper function for calling a share functionality.
// Should be used when user presses a share button/menu item.
//private fun createShareIntent(activity: Activity, plantName: String) {
//    val shareText = activity.getString(R.string.share_text_plant, plantName)
//    val shareIntent = ShareCompat.IntentBuilder(activity)
//        .setText(shareText)
//        .setType("text/plain")
//        .createChooserIntent()
//        .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
//    activity.startActivity(shareIntent)
//}