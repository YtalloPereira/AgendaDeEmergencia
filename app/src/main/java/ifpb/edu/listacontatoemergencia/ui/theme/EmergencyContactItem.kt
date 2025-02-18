package ifpb.edu.listacontatoemergencia.ui.theme

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ifpb.edu.listacontatoemergencia.models.EmergencyContact

@Composable
fun EmergencyContactItem(
    contact: EmergencyContact,
    onDeleteContact: () -> Unit
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = contact.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Telefone: ${contact.phone}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Row {
                IconButton(
                    onClick = {
                        val intent = Intent(Intent.ACTION_DIAL).apply {
                            data = Uri.parse("tel:${contact.phone}")
                        }
                        context.startActivity(intent)
                    }
                ) {
                    Icon(
                        Icons.Default.Phone,
                        contentDescription = "Ligar",
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }

                IconButton(onClick = onDeleteContact) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Excluir Contato",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}
