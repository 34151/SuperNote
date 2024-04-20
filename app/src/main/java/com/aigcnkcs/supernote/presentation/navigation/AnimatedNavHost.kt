package com.aigcnkcs.supernote.presentation.navigation

import android.content.Intent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.aigcnkcs.supernote.presentation.screen.CameraXScreen
import com.aigcnkcs.supernote.presentation.screen.FolderScreen
import com.aigcnkcs.supernote.presentation.screen.MainScreen
import com.aigcnkcs.supernote.presentation.screen.NoteScreen
import com.aigcnkcs.supernote.presentation.screen.SettingsScreen
import com.aigcnkcs.supernote.presentation.util.Constants.NAV_ANIMATION_TIME
import com.aigcnkcs.supernote.presentation.util.parseSharedContent

@Composable
fun AnimatedNavHost(
    modifier: Modifier,
    navController: NavHostController = rememberNavController(),
    isLargeScreen: Boolean
) = NavHost(
    modifier = modifier,
    navController = navController,
    startDestination = Route.MAIN
) {

    composable(
        route = Route.MAIN,
        enterTransition = { EnterTransition.None },
        exitTransition = {
            slideOutOfContainer(
                animationSpec = tween(NAV_ANIMATION_TIME),
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                targetOffset = { it / 4 }
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                animationSpec = tween(NAV_ANIMATION_TIME),
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                initialOffset = { it / 4 }
            )
        },
        popExitTransition = { ExitTransition.None }
    ) {
        MainScreen(isLargeScreen = isLargeScreen) { route ->
            navController.navigate(route)
        }
    }

    composable(
        route = Route.FOLDERS,
        enterTransition = {
            slideIntoContainer(
                animationSpec = tween(NAV_ANIMATION_TIME),
                towards = AnimatedContentTransitionScope.SlideDirection.Left
            )
        },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = {
            slideOutOfContainer(
                animationSpec = tween(NAV_ANIMATION_TIME),
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
            )
        }
    ) {
        FolderScreen {
            navController.navigateUp()
        }
    }

    composable(
        route = Route.NOTE,
        deepLinks = listOf(
            navDeepLink {
                action = Intent.ACTION_SEND
                mimeType = "text/*"
            }
        ),
        enterTransition = {
            slideIntoContainer(
                animationSpec = tween(NAV_ANIMATION_TIME),
                towards = AnimatedContentTransitionScope.SlideDirection.Left
            )
        },
        exitTransition = {
            slideOutOfContainer(
                animationSpec = tween(NAV_ANIMATION_TIME),
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                targetOffset = { it / 4 }
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                animationSpec = tween(NAV_ANIMATION_TIME),
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                initialOffset = { it / 4 }
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                animationSpec = tween(NAV_ANIMATION_TIME),
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
            )
        }
    ) {
        val sharedText = it.arguments?.getParcelable<Intent>(NavController.KEY_DEEP_LINK_INTENT)?.parseSharedContent()?.trim()
        val scannedText =
            navController.currentBackStackEntry?.savedStateHandle?.get<String>("scannedText")?.trim()
        NoteScreen(
            isLargeScreen = isLargeScreen,
            sharedText = sharedText,
            scannedText = scannedText,
            navigateUp = { navController.navigateUp() }
        ) { navController.navigate(Route.CAMERAX) }
    }

    composable(
        route = Route.CAMERAX,
        enterTransition = {
            slideIntoContainer(
                animationSpec = tween(NAV_ANIMATION_TIME),
                towards = AnimatedContentTransitionScope.SlideDirection.Left
            )
        },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = {
            slideOutOfContainer(
                animationSpec = tween(NAV_ANIMATION_TIME),
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
            )
        }
    ) {
        CameraXScreen(onCloseClick = { navController.navigateUp() }) {
            navController.previousBackStackEntry?.savedStateHandle?.set("scannedText", it)
            navController.navigateUp()
        }
    }

    composable(
        route = Route.SETTINGS,
        enterTransition = {
            slideIntoContainer(
                animationSpec = tween(NAV_ANIMATION_TIME),
                towards = AnimatedContentTransitionScope.SlideDirection.Left
            )
        },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = {
            slideOutOfContainer(
                animationSpec = tween(NAV_ANIMATION_TIME),
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
            )
        }
    ) {
        SettingsScreen {
            navController.navigateUp()
        }
    }
}
