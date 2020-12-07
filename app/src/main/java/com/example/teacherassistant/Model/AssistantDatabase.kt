package com.example.teacherassistant.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.teacherassistant.Model.Dao.CourseDao
import com.example.teacherassistant.Model.Dao.GradeDao
import com.example.teacherassistant.Model.Dao.StudentCourseDao
import com.example.teacherassistant.Model.Dao.StudentDao

@Database(
    entities = [
        Student::class,
        Course::class,
        Grade::class,
        StudentCourseCrossRef::class
    ],
    version = 1
)
@TypeConverters(Conventers::class)
abstract class AssistantDatabase:RoomDatabase(){
    abstract fun studentDao(): StudentDao
    abstract fun courseDao(): CourseDao
    abstract fun gradeDao(): GradeDao
    abstract fun studentCourseDao(): StudentCourseDao
    companion object{
        @Volatile
        private var INSTANCE: AssistantDatabase?=null

        fun getDatabase(context: Context):AssistantDatabase{
            val tempInstance= INSTANCE

            if(tempInstance != null)
                return tempInstance
            else
                synchronized(this){
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AssistantDatabase::class.java,
                        "assistant_db"
                    ).build()
                    INSTANCE = instance
                    return instance
                }
        }
    }
}