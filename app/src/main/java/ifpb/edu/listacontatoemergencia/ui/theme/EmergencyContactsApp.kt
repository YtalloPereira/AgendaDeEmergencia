package ifpb.edu.listacontatoemergencia.ui.theme


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ifpb.edu.listacontatoemergencia.data.emergencyContacts

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
                // Navegação dinâmica para cada categoria usando o script de data
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


