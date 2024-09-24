package com.app.voicenotesai.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val title: String, val filledIcon: ImageVector, val outlinedIcon: ImageVector) {
    data object Record : BottomNavItem(Routes.Record, "Record", Icons.Filled.Home, Icons.Outlined.Home)
    data object Settings : BottomNavItem(Routes.Settings, "Settings", Icons.Filled.Settings, Icons.Outlined.Settings)
}