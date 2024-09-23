package com.app.voicenotesai

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import com.app.voicenotesai.presentation.theme.VoiceNotesAITheme
import com.app.voicenotesai.presentation.screens.MyApp
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.vertexai.FirebaseVertexAI
import com.google.firebase.vertexai.vertexAI
import com.vmadalin.easypermissions.EasyPermissions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(), EasyPermissions.PermissionCallbacks {

    private val context = this
    private val TAG = "MainActivity"
    private var mUser: FirebaseUser? = null
    private val auth = FirebaseAuth.getInstance()

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

    private fun checkFirebaseAuth() {
        if (auth.currentUser != null) {
            // User is already signed in
            Log.d("$TAG-FirebaseAuth", "User is already signed in")
        } else
            initializeAnonymousUser()
    }

    override fun onStart() {
        super.onStart()
        checkFirebaseAuth()
    }

    private fun initializeAnonymousUser() {
        auth.signInAnonymously().addOnCompleteListener {
            if (it.isSuccessful) {
                mUser = auth.currentUser
                Log.d("$TAG-FirebaseAuth", "FirebaseAuth success ")
            } else {
                Log.d("$TAG-FirebaseAuth", "FirebaseAuth failed ")
            }
        }
    }

    companion object {
        val generativeModel = Firebase.vertexAI.generativeModel("gemini-1.5-flash-001")
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