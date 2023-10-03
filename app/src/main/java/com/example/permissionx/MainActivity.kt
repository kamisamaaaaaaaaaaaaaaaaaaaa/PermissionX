package com.example.permissionx

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lysdev.PermissionX

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val callBtn = findViewById<Button>(R.id.callBtn)
        callBtn.setOnClickListener {
            PermissionX.request(
                this,
                Manifest.permission.CALL_PHONE,
            ) { isAllGranted, _ ->
                if (isAllGranted) {
                    call()
                } else {
                    Toast.makeText(this, "call permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun call() {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }
}