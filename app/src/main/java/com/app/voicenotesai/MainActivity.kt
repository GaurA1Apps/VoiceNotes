package com.app.voicenotesai

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import com.app.voicenotesai.presentation.theme.VoiceNotesAITheme
import com.app.voicenotesai.presentation.screens.MyApp
import com.vmadalin.easypermissions.EasyPermissions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(), EasyPermissions.PermissionCallbacks {

    private val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VoiceNotesAITheme {
                Surface {
                    MyApp()
                }
            }
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }
}