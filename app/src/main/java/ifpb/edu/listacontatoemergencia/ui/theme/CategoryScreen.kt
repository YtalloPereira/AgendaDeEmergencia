package ifpb.edu.listacontatoemergencia.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ifpb.edu.listacontatoemergencia.models.CategoryWithContacts
import ifpb.edu.listacontatoemergencia.models.EmergencyContact

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    categoryWithContacts: CategoryWithContacts,
    navController: NavController,
    onDeleteContact: (EmergencyContact) -> Unit,
    modifier: Modifier = Modifier
) {
    val category = categoryWithContacts.category
    val contacts = categoryWithContacts.contacts

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(category.title) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (contacts.isEmpty()) {
                item {
                    Text(
                        text = "Nenhum contato nesta categoria",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
            } else {
                items(contacts) { contact ->
                    EmergencyContactItem(
                        contact = contact,
                        onDeleteContact = { onDeleteContact(contact) }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}