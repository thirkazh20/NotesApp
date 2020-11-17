package com.thirkazh.notes.data

import android.content.Context
import androidx.room.*
import com.thirkazh.notes.model.ToDoData

@Database(entities = [ToDoData::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun toDoDao(): ToDoDao

    companion object{
        @Volatile
        private var INSTANCE: TodoDatabase? = null
        fun getDatabase(context: Context): TodoDatabase{
            val tempInstance = INSTANCE
            if (tempInstance!= null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        TodoDatabase::class.java,
                        "todo_database"
                ) . build()
                INSTANCE =  instance
                return instance
            }
        }
    }
}