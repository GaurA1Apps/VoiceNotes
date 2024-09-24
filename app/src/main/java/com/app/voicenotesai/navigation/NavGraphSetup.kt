package com.app.voicenotesai.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
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
        composable(Routes.Record, enterTransition = {
            return@composable fadeIn(tween(1000))
        }, exitTransition = {
            return@composable slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
            )
        }, popEnterTransition = {
            return@composable slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End, tween(700)
            )
        }) {
            RecordScreen(recordingsViewModel, onItemClick = {
                // Handle item click and navigate to detail screen
                recordingsViewModel.setCurrentRecording(it)
                navController.navigate(Routes.RecordDetail)
            })
        }
        composable(Routes.Settings) {
            SettingsScreen()
        }

        composable(Routes.RecordDetail, enterTransition = {
            return@composable fadeIn(tween(1000))
        }, exitTransition = {
            return@composable slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
            )
        }, popEnterTransition = {
            return@composable slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End, tween(700)
            )
        }) {
            val currentRecording = recordingsViewModel.getCurrentRecording()
            if (currentRecording != null) {
                AudioRecordDetailScreen(audioRecord = currentRecording)
            }
        }
    }

}
