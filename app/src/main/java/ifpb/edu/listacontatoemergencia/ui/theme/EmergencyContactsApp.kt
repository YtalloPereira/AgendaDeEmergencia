package ifpb.edu.listacontatoemergencia.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ifpb.edu.listacontatoemergencia.data.emergencyContacts

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmergencyContactsApp(
    isDarkMode: Boolean,
    onToggleDarkMode: () -> Unit
) {
    val navController = rememberNavController()

    MaterialTheme(
        colorScheme = if (isDarkMode) darkColorScheme() else lightColorScheme()
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Agenda de Telefones de Emergência") },
                    actions = {
                        IconButton(onClick = onToggleDarkMode) {
                            Icon(
                                imageVector = if (isDarkMode) Icons.Default.LightMode
                                else Icons.Default.DarkMode,
                                contentDescription = if (isDarkMode) "Switch to Light Mode"
                                else "Switch to Dark Mode"
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.padding(paddingValues)
            ) {
                composable("home") {
                    HomeScreen(
                        navController = navController,
                        modifier = Modifier
                    )
                }
                emergencyContacts.forEach { category ->
                    composable(category.title.lowercase()) {
                        CategoryScreen(
                            category = category,
                            navController = navController,
                            modifier = Modifier
                        )
                    }
                }
            }
        }
    }
}


