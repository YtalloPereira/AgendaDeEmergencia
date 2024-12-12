package ifpb.edu.listacontatoemergencia.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ifpb.edu.listacontatoemergencia.data.emergencyContacts
import ifpb.edu.listacontatoemergencia.models.ContactCategory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmergencyContactsApp() {
    val navController = rememberNavController() // Controle de navegação

    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Agenda de Telefones de Emergência") })
            }
        ) { paddingValues ->
            // Padding adicional para o conteúdo abaixo da AppBar
            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    HomeScreen(navController = navController, modifier = Modifier.padding(top = paddingValues.calculateTopPadding()))
                }
                composable("federal") {
                    CategoryScreen(category = emergencyContacts[0], modifier = Modifier.padding(top = paddingValues.calculateTopPadding()))
                }
                composable("estadual") {
                    CategoryScreen(category = emergencyContacts[1], modifier = Modifier.padding(top = paddingValues.calculateTopPadding()))
                }
                composable("municipal") {
                    CategoryScreen(category = emergencyContacts[2], modifier = Modifier.padding(top = paddingValues.calculateTopPadding()))
                }
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        // Botões para navegar para cada categoria
        Button(
            onClick = { navController.navigate("federal") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text("Federal")
        }
        Button(
            onClick = { navController.navigate("estadual") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text("Estadual")
        }
        Button(
            onClick = { navController.navigate("municipal") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text("Municipal")
        }
    }
}


@Composable
fun CategoryScreen(category: ContactCategory, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        // Título da categoria
        Text(text = category.title, fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        // Exibição dos contatos
        LazyColumn {
            items(category.contacts) { contact ->
                EmergencyContactItem(contact = contact)
            }
        }
    }
}
