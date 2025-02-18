package ifpb.edu.listacontatoemergencia.ui.theme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ifpb.edu.listacontatoemergencia.models.EmergencyContact

@Composable
fun AddContactDialog(
    categoryId: Long,
    onAddContact: (EmergencyContact) -> Unit,
    onDismiss: () -> Unit
) {
    var contactName by remember { mutableStateOf("") }
    var contactPhone by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Adicionar Contato de Emergência") },
        text = {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = contactName,
                    onValueChange = { contactName = it },
                    label = { Text("Nome") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = contactPhone,
                    onValueChange = { contactPhone = it },
                    label = { Text("Telefone") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (contactName.isNotBlank() && contactPhone.isNotBlank()) {
                        onAddContact(
                            EmergencyContact(
                                name = contactName,
                                phone = contactPhone,
                                categoryId = categoryId
                            )
                        )
                    }
                },
                enabled = contactName.isNotBlank() && contactPhone.isNotBlank()
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