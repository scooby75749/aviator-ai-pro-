package com.aviator.aipro

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.*

class MainActivity : Activity() {

    private lateinit var statusText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.gravity = Gravity.CENTER
        layout.setPadding(30, 40, 30, 40)
        layout.setBackgroundColor(Color.rgb(15, 15, 15))

        val title = TextView(this)
        title.text = "AVIATOR AI PRO"
        title.textSize = 30f
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
        statusText.text = "AI Status: Ready"
        statusText.textSize = 18f
        statusText.setTextColor(Color.GREEN)
        statusText.gravity = Gravity.CENTER
        statusText.setPadding(10, 40, 10, 40)

        layout.addView(statusText)

        val startButton = Button(this)
        startButton.text = "START ANALYSIS"

        startButton.setOnClickListener {
            statusText.text =
                "Analysis engine setup pending"
        }

        layout.addView(startButton)

        val historyButton = Button(this)
        historyButton.text = "ROUND HISTORY"

        historyButton.setOnClickListener {
            statusText.text =
                "Round history module coming next"
        }

        layout.addView(historyButton)

        val predictionButton = Button(this)
        predictionButton.text = "NEXT ROUND ANALYSIS"

        predictionButton.setOnClickListener {
            statusText.text =
                "Historical data required for analysis"
        }

        layout.addView(predictionButton)

        setContentView(layout)
    }
}
