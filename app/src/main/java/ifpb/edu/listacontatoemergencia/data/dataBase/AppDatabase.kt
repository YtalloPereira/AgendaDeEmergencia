package ifpb.edu.listacontatoemergencia.data.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ifpb.edu.listacontatoemergencia.data.dao.ContactCategoryDao
import ifpb.edu.listacontatoemergencia.data.dao.EmergencyContactDao
import ifpb.edu.listacontatoemergencia.models.ContactCategory
import ifpb.edu.listacontatoemergencia.models.EmergencyContact

@Database(
    entities = [EmergencyContact::class, ContactCategory::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun emergencyContactDao(): EmergencyContactDao
    abstract fun contactCategoryDao(): ContactCategoryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "emergency_contacts_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}