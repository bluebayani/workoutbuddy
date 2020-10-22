package com.example.workoutbuddy.activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.graphics.ColorUtils
import com.example.workoutbuddy.R
import kotlinx.android.synthetic.main.exercise_popup_card.*

class ExercisePopupWindow : AppCompatActivity() {

    private lateinit var popupTitle: TextView
    // private lateinit var popupTitleBelow: TextView
    private lateinit var popupDesc: TextView
    private lateinit var popupButton: Button




    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)
        setContentView(R.layout.activity_exercise_popup_window)


        popupTitle = findViewById(R.id.popuptitle)
        // popupTitleBelow
        popupDesc = findViewById(R.id.popupdesctext)
        popupButton = findViewById(R.id.popupbttn)


        val bundle = intent.extras
        popupTitle.text = bundle?.getString("eName", "Title") ?: ""
        popupDesc.text = bundle?.getString("eDescr", "Text") ?: ""


        this.window.statusBarColor = Color.TRANSPARENT

//        // Fade animation for the background of Popup Window
//        val alpha = 100 //between 0-255
//        val alphaColor = ColorUtils.setAlphaComponent(Color.parseColor("#000000"), alpha)
//        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), Color.TRANSPARENT, alphaColor)
//        colorAnimation.duration = 500 // milliseconds
//        colorAnimation.addUpdateListener { animator ->
//            layout.setBackgroundColor(animator.animatedValue as Int)
//        }
//        colorAnimation.start()

        // fade animation when card opens up
        layout.alpha = 0f
        layout.animate().alpha(1f).setDuration(500).setInterpolator(
            DecelerateInterpolator()
        ).start()


        // Dismiss button,
        popupButton.setOnClickListener {
            // Fade animation for the background of Popup Window when you press the back button
            val alpha = 100 // between 0-255
            val alphaColor = ColorUtils.setAlphaComponent(Color.parseColor("#000000"), alpha)
            val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), alphaColor, Color.TRANSPARENT)
            colorAnimation.duration = 500 // milliseconds
            colorAnimation.addUpdateListener { animator ->
                layout.setBackgroundColor(
                    animator.animatedValue as Int
                )
            }

            // Fade animation for the Popup Window when you press the back button
            layout.animate().alpha(0f).setDuration(500).setInterpolator(
                DecelerateInterpolator()
            ).start()

            // After animation finish, close the Activity
            colorAnimation.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    finish()
                    //overridePendingTransition(0, 0)
                }
            })
            colorAnimation.start()
        }

    }
    
}