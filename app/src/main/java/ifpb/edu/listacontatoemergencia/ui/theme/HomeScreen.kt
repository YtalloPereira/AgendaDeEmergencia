package ifpb.edu.listacontatoemergencia.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ifpb.edu.listacontatoemergencia.models.CategoryWithContacts
import ifpb.edu.listacontatoemergencia.models.ContactCategory

@Composable
fun HomeScreen(
    categoriesWithContacts: List<CategoryWithContacts>,
    navController: NavController,
    onAddCategory: (ContactCategory) -> Unit,
    onDeleteCategory: (ContactCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    var showAddCategoryDialog by remember { mutableStateOf(false) }

    Column(modifier = modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Categorias de Contatos",
                style = MaterialTheme.typography.headlineSmall
            )

            IconButton(onClick = { showAddCategoryDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar Categoria")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        categoriesWithContacts.forEach { categoryWithContacts ->
            val category = categoryWithContacts.category
            val contactCount = categoryWithContacts.contacts.size

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.small
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = category.title,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "$contactCount contatos",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Row {
                        Button(onClick = { navController.navigate("category/${category.id}") }) {
                            Text("Ver Contatos")
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        IconButton(onClick = { onDeleteCategory(category) }) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Excluir Categoria",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }

    if (showAddCategoryDialog) {
        AddCategoryDialog(
            onAddCategory = {
                onAddCategory(it)
                showAddCategoryDialog = false
            },
            onDismiss = { showAddCategoryDialog = false }
        )
    }
}

@Composable
fun AddCategoryDialog(
    onAddCategory: (ContactCategory) -> Unit,
    onDismiss: () -> Unit
) {
    var categoryTitle by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Adicionar Categoria") },
        text = {
            Column {
                OutlinedTextField(
                    value = categoryTitle,
                    onValueChange = { categoryTitle = it },
                    label = { Text("Título da Categoria") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (categoryTitle.isNotBlank()) {
                        onAddCategory(ContactCategory(title = categoryTitle))
                    }
                },
                enabled = categoryTitle.isNotBlank()
            ) {
                Text("Adicionar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}