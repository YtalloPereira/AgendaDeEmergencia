package ifpb.edu.listacontatoemergencia.data.dao


import androidx.room.*
import ifpb.edu.listacontatoemergencia.models.EmergencyContact
import kotlinx.coroutines.flow.Flow

@Dao
interface EmergencyContactDao {
    @Query("SELECT * FROM emergency_contacts")
    fun getAllContacts(): Flow<List<EmergencyContact>>

    @Query("SELECT * FROM emergency_contacts WHERE categoryId = :categoryId")
    fun getContactsByCategoryId(categoryId: Long): Flow<List<EmergencyContact>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContacts(contacts: List<EmergencyContact>)

    @Update
    suspend fun updateContact(contact: EmergencyContact)

    @Delete
    suspend fun deleteContact(contact: EmergencyContact)

    @Query("DELETE FROM emergency_contacts WHERE id = :id")
    suspend fun deleteContactById(id: Long)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertContact(contact: EmergencyContact): Long

    @Query("SELECT COUNT(*) FROM emergency_contacts")
    suspend fun getContactCount(): Int
}