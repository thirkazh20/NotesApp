package com.thirkazh.notes.viewmodel

import android.app.Application
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.thirkazh.notes.R
import com.thirkazh.notes.data.Priority
import com.thirkazh.notes.model.ToDoData

class SharedViewModel(application: Application): AndroidViewModel(application) {
    val emptyDatabase : MutableLiveData<Boolean> = MutableLiveData(false)

     fun checkDatabaseEmpty(toDoData: List<ToDoData>){
         emptyDatabase.value = toDoData.isEmpty()
     }

    val listener: AdapterView.OnItemSelectedListener = object : AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when(position){
                0 -> {(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.red))}
                1 -> {(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.yellow))}
                2 -> {(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.green))}
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

    }

    fun verifyDataFromUser(title: String, description: String): Boolean{
        return !(title.isEmpty() || description.isEmpty())
    }

    fun parsePriority(priority: String): Priority{
        return when(priority){
            "High Priority" ->{
                Priority.HIGH
            }

            "Medium Priority" ->{
                Priority.MEDIUM
            }

            "Low Priority" ->{
                Priority.LOW
            } else -> Priority.LOW
        }
    }
}