package com.example.workoutbuddy.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.workoutbuddy.ExerciseItem
import com.example.workoutbuddy.R
import java.lang.StringBuilder

class NewExerciseActivity : AppCompatActivity() {

    private lateinit var newExerciseNameEditText: EditText
    private lateinit var chest: CheckBox
    private lateinit var back: CheckBox
    private lateinit var triceps: CheckBox
    private lateinit var biceps: CheckBox
    private lateinit var shoulders: CheckBox
    private lateinit var glutes: CheckBox
    private lateinit var quads: CheckBox
    private lateinit var hamstrings: CheckBox
    private lateinit var core: CheckBox
    private lateinit var newExerciseDescriptionEditText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_exercise)

        newExerciseNameEditText = findViewById(R.id.newExerciseNameEditText)
        chest = findViewById(R.id.chestExButton)
        back = findViewById(R.id.backExButton)
        triceps = findViewById(R.id.tricepsExButton)
        biceps = findViewById(R.id.bicepsExButton)
        shoulders = findViewById(R.id.shouldersExButton)
        glutes = findViewById(R.id.glutesExButton)
        quads = findViewById(R.id.quadsExButton)
        hamstrings = findViewById(R.id.hamsExButton)
        core = findViewById(R.id.coreExButton)
        newExerciseDescriptionEditText = findViewById(R.id.newExerciseDescriptionEditText)

        val submitButton: Button = findViewById<Button>(R.id.createNewExerciseButton)
        val mStartActBtn = findViewById<Button>(R.id.startActBtn)



        // Handle create new exercise button
        submitButton.setOnClickListener {

            val type = StringBuilder()
            // Observe which Exercise Types are chosen
            if (chest.isChecked) { type.append("Chest ") }
            if (back.isChecked) { type.append("Back ") }
            if (triceps.isChecked) { type.append("Triceps ") }
            if (biceps.isChecked) { type.append("Biceps ") }
            if (shoulders.isChecked) { type.append("Shoulders ") }
            if (glutes.isChecked) { type.append("Glutes ") }
            if (quads.isChecked) { type.append("Quadriceps ") }
            if (hamstrings.isChecked) { type.append("Hamstrings ") }
            if (core.isChecked) { type.append("Core ") }



            // Create intent to reply to Exercise List
            val replyIntent = Intent()

            // || TextUtils.isEmpty(newExerciseDescriptionEditText.text) || eTypes.isEmpty()

            // Input Validation: CANCEL IF ANYTHING IS MISSING
            if (TextUtils.isEmpty(newExerciseNameEditText.text) || TextUtils.isEmpty(newExerciseDescriptionEditText.text) || type.toString().isEmpty())  {
                Toast.makeText(this, "UNSUCCESSFUL EX POST, SOMETHING EMPTY", Toast.LENGTH_SHORT).show()
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val eName = newExerciseNameEditText.text.toString()
                val eType = type.toString()
                val eDescr = newExerciseDescriptionEditText.text.toString()

                // Toast.makeText(this, "NAME: $eName TYPE: $eType DESCR: $eDescr", Toast.LENGTH_LONG).show()

                replyIntent.putExtra("eName", eName)
                replyIntent.putExtra("eType", eType)
                replyIntent.putExtra("eDescr", eDescr)

                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

        // Handle back button click
        mStartActBtn.setOnClickListener {
            //Destroy Actviity
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.exerciselistsql.REPLY"
    }


}