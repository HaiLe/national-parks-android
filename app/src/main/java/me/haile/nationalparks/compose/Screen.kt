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

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route: String, val navArguments: List<NamedNavArgument> = emptyList()
) {
    data object Home : Screen("home")

    data object Park : Screen(
        route = "park/{parkId}/{parkTitle}",
        navArguments = listOf(navArgument("parkId") {
            type = NavType.StringType
        }, navArgument("parkTitle") {
            type = NavType.StringType
        })
    ) {
        fun createRoute(parkId: String, parkTitle: String) =
            "park/${parkId}/${parkTitle}"
    }
    data object Article : Screen(
        route = "article/{articleId}/{articleTitle}",
        navArguments = listOf(navArgument("articleId") {
            type = NavType.StringType
        }, navArgument("articleTitle") {
            type = NavType.StringType
        })
    ) {
        fun createRoute(articleId: String, articleTitle: String) =
            "article/${articleId}/${articleTitle}"
    }

    data object Gallery : Screen(
        route = "gallery/{query}", navArguments = listOf(navArgument("query") {
            type = NavType.StringType
        })
    ) {
        fun createRoute(plantName: String) = "gallery/${plantName}"
    }
}