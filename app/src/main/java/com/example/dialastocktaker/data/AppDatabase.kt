package com.example.dialastocktaker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dialastocktaker.data.dao.JobDao
import com.example.dialastocktaker.data.dao.MessageDao
import com.example.dialastocktaker.data.dao.NotificationDao
import com.example.dialastocktaker.data.dao.ReportDao
import com.example.dialastocktaker.data.dao.UserDao
import com.example.dialastocktaker.data.entity.Job
import com.example.dialastocktaker.data.entity.Message
import com.example.dialastocktaker.data.entity.Notification
import com.example.dialastocktaker.data.entity.Report
import com.example.dialastocktaker.data.entity.User
import com.example.dialastocktaker.data.util.Converters

@Database(
    entities = [User::class, Job::class, Message::class, Notification::class, Report::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun jobDao(): JobDao
    abstract fun messageDao(): MessageDao
    abstract fun notificationDao(): NotificationDao
    abstract fun reportDao(): ReportDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "stocktaker_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
