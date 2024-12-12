import androidx.compose.foundation.layout.fillMaxSize // Importando o modificador fillMaxSize
import androidx.compose.foundation.layout.padding // Importando o modificador padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ifpb.edu.listacontatoemergencia.models.ContactCategory
import androidx.compose.ui.Modifier // Importando Modifier para usar os modificadores
import ifpb.edu.listacontatoemergencia.ui.theme.EmergencyContactItem

@Composable
fun EmergencyContactsList(contacts: List<ContactCategory>) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        contacts.forEach { category ->
            // Cabeçalho da categoria
            item {
                Text(
                    text = category.title,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            // Lista de contatos dentro da categoria
            items(category.contacts) { contact ->
                EmergencyContactItem(contact)
            }
        }
    }
}

