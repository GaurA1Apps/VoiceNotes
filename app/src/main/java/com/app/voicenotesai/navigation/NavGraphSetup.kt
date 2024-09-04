package com.app.voicenotesai.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.voicenotesai.presentation.screens.RecordScreen
import com.app.voicenotesai.presentation.screens.RecordingsViewModel
import com.app.voicenotesai.presentation.screens.SettingsScreen

@Composable
fun NavGraphSetup(navController: NavHostController, paddingValues: PaddingValues, recordingsViewModel: RecordingsViewModel) {

    NavHost(
        navController = navController,
        startDestination = Routes.Record,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(Routes.Record) {
            RecordScreen(recordingsViewModel)
        }
        composable(Routes.Settings) {
            SettingsScreen(paddingValues)
        }
    }

}
