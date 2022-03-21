package com.example.showskillandroid

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat

class Permissions {
    fun takePictureWithPreview(requestMultiplePermissionsLauncher: ActivityResultLauncher<Array<(String) >>, context: Context) {
        //requestMultiplePermissionsLauncher.launch(arrayOf(Manifest.permission.CAMERA))
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
        } else {
            requestMultiplePermissionsLauncher.launch(
                arrayOf(
                    Manifest.permission.INTERNET,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            )
//            requestMultiplePermissionsLauncher.launch(arrayOf(Manifest.permission.CAMERA))
        }
    }

}