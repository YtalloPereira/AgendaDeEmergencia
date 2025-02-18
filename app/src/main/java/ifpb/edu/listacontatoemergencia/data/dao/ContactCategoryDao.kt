package ifpb.edu.listacontatoemergencia.data.dao

import androidx.room.*
import ifpb.edu.listacontatoemergencia.models.ContactCategory
import ifpb.edu.listacontatoemergencia.models.CategoryWithContacts
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactCategoryDao {
    @Query("SELECT * FROM contact_categories")
    fun getAllCategories(): Flow<List<ContactCategory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<ContactCategory>)

    @Update
    suspend fun updateCategory(category: ContactCategory)

    @Delete
    suspend fun deleteCategory(category: ContactCategory)

    @Transaction
    @Query("SELECT * FROM contact_categories")
    fun getCategoriesWithContacts(): Flow<List<CategoryWithContacts>>

    @Transaction
    @Query("SELECT * FROM contact_categories WHERE id = :categoryId")
    fun getCategoryWithContacts(categoryId: Long): Flow<CategoryWithContacts>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(category: ContactCategory): Long

    @Query("SELECT COUNT(*) FROM contact_categories")
    suspend fun getCategoryCount(): Int
}