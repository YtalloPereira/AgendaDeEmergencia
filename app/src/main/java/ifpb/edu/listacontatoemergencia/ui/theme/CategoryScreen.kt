package ifpb.edu.listacontatoemergencia.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ifpb.edu.listacontatoemergencia.models.ContactCategory

@Composable
fun CategoryScreen(category: ContactCategory, navController: NavController, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        // Título
        Text(text = category.title, fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        // Contatos
        LazyColumn {
            items(category.contacts) { contact ->
                EmergencyContactItem(contact = contact)
            }
        }

        // Botão de Voltar
        Button(
            onClick = { navController.popBackStack("home", false) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp) // Espaçamento acima do botão
        ) {
            Text("Voltar")
        }
    }
}