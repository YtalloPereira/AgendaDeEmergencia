package ifpb.edu.listacontatoemergencia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ifpb.edu.listacontatoemergencia.data.UserPreferencesRepository
import ifpb.edu.listacontatoemergencia.ui.theme.EmergencyContactsApp
import ifpb.edu.listacontatoemergencia.ui.theme.MainViewModel
import ifpb.edu.listacontatoemergencia.ui.theme.MainViewModelFactory

class MainActivity : ComponentActivity() {
    private val userPreferencesRepository by lazy { UserPreferencesRepository(applicationContext) }
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(userPreferencesRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkMode by viewModel.isDarkMode.collectAsState()
            EmergencyContactsApp(
                isDarkMode = isDarkMode,
                onToggleDarkMode = viewModel::toggleDarkMode
            )
        }
    }
}