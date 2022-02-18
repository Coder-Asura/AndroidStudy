package com.asura.android_study.ui.permission

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.asura.android_study.R

class PermissionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.content, PermissionFragment())
        transaction.commit()
        findViewById<Button>(R.id.button1).setOnClickListener {
            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA),
            100
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100) {
            val notAllowList = mutableListOf<String>()
            grantResults.forEachIndexed { index, i ->
                if (i != PackageManager.PERMISSION_GRANTED) {
                    notAllowList.add(permissions[index])
                }
            }
            if (notAllowList.isEmpty()) {
                Toast.makeText(this, "Activity 全部允许", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Activity $notAllowList 没允许", Toast.LENGTH_SHORT).show()
            }
        }
    }
}