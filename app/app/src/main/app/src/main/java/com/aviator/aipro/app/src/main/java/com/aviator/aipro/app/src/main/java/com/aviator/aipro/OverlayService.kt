package com.aviator.aipro

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.view.Gravity
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class OverlayService : Service() {

    private lateinit var windowManager: WindowManager
    private var floatingView: LinearLayout? = null

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()

        windowManager =
            getSystemService(WINDOW_SERVICE) as WindowManager

        showFloatingWindow()
    }

    private fun showFloatingWindow() {

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(20, 20, 20, 20)
        layout.setBackgroundColor(0xFF202020.toInt())

        val title = TextView(this)
        title.text = "AVIATOR AI PRO"
        title.textSize = 18f
        title.setTextColor(0xFFFF3333.toInt())

        val status = TextView(this)
        status.text = "AI Analysis Ready"
        status.setTextColor(0xFFFFFFFF.toInt())

        val close = Button(this)
        close.text = "CLOSE"

        close.setOnClickListener {
            stopSelf()
        }

        layout.addView(title)
        layout.addView(status)
        layout.addView(close)

        val windowType =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            else
                WindowManager.LayoutParams.TYPE_PHONE

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            windowType,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        params.gravity = Gravity.TOP or Gravity.END
        params.x = 20
        params.y = 150

        windowManager.addView(layout, params)
        floatingView = layout
    }

    override fun onDestroy() {
        super.onDestroy()

        floatingView?.let {
            windowManager.removeView(it)
        }

        floatingView = null
    }
}
