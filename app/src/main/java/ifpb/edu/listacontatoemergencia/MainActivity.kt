package ifpb.edu.listacontatoemergencia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ifpb.edu.listacontatoemergencia.ui.theme.EmergencyContactsApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmergencyContactsApp()
        }
    }
}
