package com.aviator.aipro

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.*
import android.graphics.Color
import android.view.Gravity

class MainActivity : Activity() {

private lateinit var statusText: TextView

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    createDashboard()
}

private fun createDashboard() {

    val layout = LinearLayout(this)
    layout.orientation = LinearLayout.VERTICAL
    layout.gravity = Gravity.CENTER
    layout.setPadding(30, 40, 30, 40)
    layout.setBackgroundColor(Color.rgb(15, 15, 15))

    val title = TextView(this)
    title.text = "AVIATOR AI PRO"
    title.textSize = 28f
    title.setTextColor(Color.RED)
    title.gravity = Gravity.CENTER

    layout.addView(title)

    val subtitle = TextView(this)
    subtitle.text = "Advanced AI Analysis"
    subtitle.textSize = 16f
    subtitle.setTextColor(Color.WHITE)
    subtitle.gravity = Gravity.CENTER

    layout.addView(subtitle)

    statusText = TextView(this)
    statusText.text = "Status: Ready"
    statusText.textSize = 18f
    statusText.setTextColor(Color.GREEN)
    statusText.gravity = Gravity.CENTER
    statusText.setPadding(10, 40, 10, 40)

    layout.addView(statusText)

    val startButton = Button(this)
    startButton.text = "START FLOATING WINDOW"

    startButton.setOnClickListener {

        if (Settings.canDrawOverlays(this)) {

            try {
                val serviceIntent =
                    Intent(this, OverlayService::class.java)

                startService(serviceIntent)

                statusText.text = "Floating window started"

            } catch (e: Exception) {
                statusText.text =
                    "Service error: ${e.message}"
            }

        } else {

            statusText.text =
                "Please allow overlay permission"

            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:$packageName")
            )

            startActivity(intent)
        }
    }

    layout.addView(startButton)

    val stopButton = Button(this)
    stopButton.text = "STOP FLOATING WINDOW"

    stopButton.setOnClickListener {

        stopService(
            Intent(this, OverlayService::class.java)
        )

        statusText.text = "Floating window stopped"
    }

    layout.addView(stopButton)

    setContentView(layout)
}

}
