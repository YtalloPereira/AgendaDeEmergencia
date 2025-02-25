package ifpb.edu.listacontatoemergencia.ui.theme

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import ifpb.edu.listacontatoemergencia.data.UserPreferencesRepository
import ifpb.edu.listacontatoemergencia.data.dataBase.AppDatabase
import ifpb.edu.listacontatoemergencia.data.repository.EmergencyContactsRepository
import ifpb.edu.listacontatoemergencia.models.CategoryWithContacts
import ifpb.edu.listacontatoemergencia.models.EmergencyContact
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application,
    private val userPreferencesRepository: UserPreferencesRepository
) : AndroidViewModel(application) {
    private val repository: EmergencyContactsRepository

    val allCategoriesWithContacts: StateFlow<List<CategoryWithContacts>>

    val isDarkMode: StateFlow<Boolean> = userPreferencesRepository.darkModeFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = false
        )

    init {
        val database = AppDatabase.getDatabase(application)
        val emergencyContactDao = database.emergencyContactDao()
        val contactCategoryDao = database.contactCategoryDao()
        repository = EmergencyContactsRepository(emergencyContactDao, contactCategoryDao)
        allCategoriesWithContacts = repository.allCategoriesWithContacts
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
                initialValue = emptyList()
            )

        // Preenche o banco de dados se estiver vazio
        viewModelScope.launch {
            val categories = repository.allCategories.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = emptyList()
            ).value

            if (categories.isEmpty()) {
                repository.prepopulateDatabase()
            }
        }
    }

    fun toggleDarkMode() {
        viewModelScope.launch {
            userPreferencesRepository.setDarkMode(!isDarkMode.value)
        }
    }

    // Funções para gerenciar contatos
    fun addContact(contact: EmergencyContact) {
        viewModelScope.launch {
            repository.insertContact(contact)
        }
    }

    fun deleteContact(contact: EmergencyContact) {
        viewModelScope.launch {
            repository.deleteContact(contact)
        }
    }
}

class MainViewModelFactory(
    private val application: Application,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(application, userPreferencesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}