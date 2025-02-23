package ifpb.edu.listacontatoemergencia.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ifpb.edu.listacontatoemergencia.models.CategoryWithContacts

@Composable
fun HomeScreen(
    categoriesWithContacts: List<CategoryWithContacts>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Esferas",
                style = MaterialTheme.typography.headlineSmall
            )

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
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
