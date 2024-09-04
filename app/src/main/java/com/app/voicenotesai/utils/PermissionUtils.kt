package com.app.voicenotesai.utils

import android.Manifest
import android.content.Context
import com.app.voicenotesai.MainActivity
import com.vmadalin.easypermissions.EasyPermissions

class PermissionUtils(
    private val context: Context,
) {

    companion object {
        const val RECORD_AUDIO_PERMISSION_REQUEST_CODE = 101
    }

    fun hasRecordAudioPermission(): Boolean {
        return EasyPermissions.hasPermissions(context, Manifest.permission.RECORD_AUDIO)
    }

    fun requestRecordAudioPermission() {
        EasyPermissions.requestPermissions(
            context as MainActivity,
            "This application needs access to your microphone to record audio",
            RECORD_AUDIO_PERMISSION_REQUEST_CODE,
            Manifest.permission.RECORD_AUDIO
        )
    }


}