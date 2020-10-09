package com.example.workoutbuddy

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.*

class WorkoutAdapter(var context: Context, var workoutList: ArrayList<WorkoutItem>): BaseAdapter() {

    override fun getItem(position: Int): Any {
        return workoutList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return workoutList.size
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

        // ITERATION 1: use of Toast to check if proper values can be accessed
        workoutCardView.setOnClickListener{
            Toast.makeText(workoutCardView.context, "Workout Description: " + workoutList[position].category, Toast.LENGTH_SHORT).show()
        }

        return workoutCardView

    }


}
