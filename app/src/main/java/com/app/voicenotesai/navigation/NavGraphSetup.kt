package com.app.voicenotesai.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.voicenotesai.presentation.screens.record_detail.AudioRecordDetailScreen
import com.app.voicenotesai.presentation.screens.record_screen.RecordScreen
import com.app.voicenotesai.presentation.screens.record_screen.RecordingsViewModel
import com.app.voicenotesai.presentation.screens.settings_screen.SettingsScreen
import java.io.File

@Composable
fun NavGraphSetup(
    navController: NavHostController,
    paddingValues: PaddingValues,
    recordingsViewModel: RecordingsViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Record,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(
            route = Routes.Record,
            enterTransition = {
                fadeIn(tween(700))
            },
            exitTransition = {
                fadeOut(tween(700))
            }
        ) {
            RecordScreen(recordingsViewModel, onItemClick = {
                recordingsViewModel.setCurrentRecording(it)
                navController.navigate(Routes.RecordDetail)
            })
        }

        composable(
            route = Routes.RecordDetail,
            enterTransition = {
                // Vertical slide-in transition
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Up, tween(700)
                )
            },
            exitTransition = {
                // Vertical slide-out transition
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Down, tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Up, tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Down, tween(700)
                )
            }

        ) {
            val currentRecording = recordingsViewModel.getCurrentRecording()
            if (currentRecording != null) {
                AudioRecordDetailScreen(
                    audioRecord = currentRecording,
                    onBackClick = { navController.navigateUp() }
                )
            }
        }

        composable(Routes.Settings) {
            SettingsScreen()
        }
    }
}
