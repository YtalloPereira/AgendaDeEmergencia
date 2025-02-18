package ifpb.edu.listacontatoemergencia.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_categories")
data class ContactCategory(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String
)
