package com.example.workoutbuddy

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat.startActivity
import com.example.workoutbuddy.Data.WorkoutItem
import com.example.workoutbuddy.activities.WorkoutNav.StartWorkoutActivity

class WorkoutAdapter(var context: Context, var workoutList: List<WorkoutItem>): BaseAdapter() {

    override fun getItem(position: Int): Any {
        return workoutList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return workoutList.size
    }

    internal fun setWorkouts(workouts: List<WorkoutItem>) {
        this.workoutList = workouts
        notifyDataSetChanged()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // inflate individual Workout Card
        val workoutCardView: View = View.inflate(context, R.layout.workout_item, null)

        // initialize items in relation to workout_item.xml
        val workoutImages: ImageView = workoutCardView.findViewById(R.id.workoutImage)
        val workoutNames: TextView = workoutCardView.findViewById(R.id.workoutName)
        val workoutCategories: TextView = workoutCardView.findViewById(R.id.workoutCat)

        // initialize individual cards based on indices.
        val myWorkoutItem: WorkoutItem = workoutList[position]

        // fill workout card with data
        workoutImages.setImageResource(myWorkoutItem.workoutImageResource)
        workoutNames.text = myWorkoutItem.name
        workoutCategories.text = myWorkoutItem.category


        workoutImages.setOnClickListener {
            //Toast.makeText(context, "Workout Description: " + workoutList[position].description, Toast.LENGTH_SHORT).show()
            val intent = Intent(context, StartWorkoutActivity::class.java)
            intent.putExtra("wName", workoutList[position].name)
            intent.putExtra("wCategory", workoutList[position].category)
            intent.putExtra("wDesc", workoutList[position].description)
            intent.putExtra("wImage", workoutList[position].workoutImageResource)
            //intent.putExtra("wReps", workoutList[position].reps)
            //intent.putExtra("wSets", workoutList[position].sets)
            startActivity(context,intent, Bundle.EMPTY)
        }
        return workoutCardView
    }
}

