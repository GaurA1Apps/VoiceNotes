package com.app.voicenotesai.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val title: String, val icon: ImageVector) {
    data object Record : BottomNavItem(Routes.Record, "Record", Icons.Default.Home)
    data object Settings : BottomNavItem(Routes.Settings, "Settings", Icons.Default.Settings)
}