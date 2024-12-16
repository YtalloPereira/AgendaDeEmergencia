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
    val navController = rememberNavController()

    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Agenda de Telefones de Emergência") }
                )
            }
        ) { paddingValues ->

            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    HomeScreen(navController = navController, modifier = Modifier.padding(top = paddingValues.calculateTopPadding()))
                }
                // Navegação dinâmica para cada categoria
                emergencyContacts.forEach { category ->
                    composable(category.title.lowercase()) {
                        CategoryScreen(
                            category = category,
                            navController = navController,
                            modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
                        )
                    }
                }
            }
        }
    }
}

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

        // Botão Voltar
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

