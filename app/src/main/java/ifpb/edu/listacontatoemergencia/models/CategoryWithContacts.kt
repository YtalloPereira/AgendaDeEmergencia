package ifpb.edu.listacontatoemergencia.models
import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithContacts(
    @Embedded val category: ContactCategory,
    @Relation(
        parentColumn = "id",
        entityColumn = "categoryId"
    )
    val contacts: List<EmergencyContact>
)
