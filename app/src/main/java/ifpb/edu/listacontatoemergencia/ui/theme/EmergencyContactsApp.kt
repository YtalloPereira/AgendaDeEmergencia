import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ifpb.edu.listacontatoemergencia.models.CategoryWithContacts
import ifpb.edu.listacontatoemergencia.models.ContactCategory
import ifpb.edu.listacontatoemergencia.models.EmergencyContact
import ifpb.edu.listacontatoemergencia.ui.theme.AddContactDialog
import ifpb.edu.listacontatoemergencia.ui.theme.CategoryScreen
import ifpb.edu.listacontatoemergencia.ui.theme.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmergencyContactsApp(
    isDarkMode: Boolean,
    onToggleDarkMode: () -> Unit,
    categoriesWithContacts: List<CategoryWithContacts>,
    onAddContact: (EmergencyContact) -> Unit,
    onUpdateContact: (EmergencyContact) -> Unit,
    onDeleteContact: (EmergencyContact) -> Unit,
    onAddCategory: (ContactCategory) -> Unit,
    onUpdateCategory: (ContactCategory) -> Unit,
    onDeleteCategory: (ContactCategory) -> Unit
) {
    val navController = rememberNavController()
    var showAddContactDialog by remember { mutableStateOf(false) }
    var selectedCategoryId by remember { mutableStateOf<Long?>(null) }

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
            },
            floatingActionButton = {
                if (selectedCategoryId != null) {
                    FloatingActionButton(onClick = { showAddContactDialog = true }) {
                        Icon(Icons.Default.Add, contentDescription = "Adicionar Contato")
                    }
                }
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.padding(paddingValues)
            ) {
                composable("home") {
                    selectedCategoryId = null
                    HomeScreen(
                        categoriesWithContacts = categoriesWithContacts,
                        navController = navController,
                        onAddCategory = { onAddCategory(it) },
                        onDeleteCategory = { onDeleteCategory(it) },
                        modifier = Modifier
                    )
                }

                categoriesWithContacts.forEach { categoryWithContacts ->
                    val category = categoryWithContacts.category
                    composable("category/${category.id}") {
                        selectedCategoryId = category.id
                        CategoryScreen(
                            categoryWithContacts = categoryWithContacts,
                            navController = navController,
                            onDeleteContact = { onDeleteContact(it) },
                            modifier = Modifier
                        )
                    }
                }
            }

            if (showAddContactDialog) {
                selectedCategoryId?.let { categoryId ->
                    AddContactDialog(
                        categoryId = categoryId,
                        onAddContact = {
                            onAddContact(it)
                            showAddContactDialog = false
                        },
                        onDismiss = { showAddContactDialog = false }
                    )
                }
            }
        }
    }
}


