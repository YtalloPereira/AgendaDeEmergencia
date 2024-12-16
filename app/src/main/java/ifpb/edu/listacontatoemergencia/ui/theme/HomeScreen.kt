package ifpb.edu.listacontatoemergencia.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ifpb.edu.listacontatoemergencia.data.emergencyContacts

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        // Botões de categoria gerados dinamicamente
        emergencyContacts.forEach { category ->
            Button(
                onClick = { navController.navigate(category.title.lowercase()) },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            ) {
                Text(category.title)
            }
        }
    }
}