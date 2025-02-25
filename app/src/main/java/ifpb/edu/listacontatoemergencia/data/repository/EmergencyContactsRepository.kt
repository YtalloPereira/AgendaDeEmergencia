package ifpb.edu.listacontatoemergencia.data.repository

import ifpb.edu.listacontatoemergencia.data.dao.ContactCategoryDao
import ifpb.edu.listacontatoemergencia.data.dao.EmergencyContactDao
import ifpb.edu.listacontatoemergencia.models.CategoryWithContacts
import ifpb.edu.listacontatoemergencia.models.ContactCategory
import ifpb.edu.listacontatoemergencia.models.EmergencyContact
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class EmergencyContactsRepository(
    private val emergencyContactDao: EmergencyContactDao,
    private val contactCategoryDao: ContactCategoryDao
) {
    suspend fun insertContact(contact: EmergencyContact): Long {
        return emergencyContactDao.insertContact(contact)
    }

    suspend fun deleteContact(contact: EmergencyContact) {
        emergencyContactDao.deleteContact(contact)
    }

    // Operações para categorias
    val allCategories: Flow<List<ContactCategory>> = contactCategoryDao.getAllCategories()
    val allCategoriesWithContacts: Flow<List<CategoryWithContacts>> = contactCategoryDao.getCategoriesWithContacts()

    fun prepopulateDatabase() {
        CoroutineScope(Dispatchers.IO).launch {
            val categoryCount = contactCategoryDao.getCategoryCount()
            val contactCount = emergencyContactDao.getContactCount()

            if (categoryCount == 0) {
                val federalCategory = ContactCategory(id = 1, title = "Federal")
                val istadualCategory = ContactCategory(id = 2, title = "Estadual")
                val municipalCategory = ContactCategory(id = 3, title = "Municipal")

                contactCategoryDao.insertCategory(federalCategory)
                contactCategoryDao.insertCategory(istadualCategory)
                contactCategoryDao.insertCategory(municipalCategory)

                val emergencyContacts = listOf(
                    EmergencyContact(name = "Polícia Federal", phone = "194", categoryId = 1),
                    EmergencyContact(name = "Rodoviária Federal", phone = "191", categoryId = 1),
                    EmergencyContact(name = "IBAMA", phone = "0800-618080", categoryId = 1),
                    EmergencyContact(name = "Receita Federal", phone = "146", categoryId = 1),
                    EmergencyContact(name = "Aeronáutica (Polícia)", phone = "190", categoryId = 1),

                    EmergencyContact(name = "Polícia Militar", phone = "190", categoryId = 2),
                    EmergencyContact(name = "Bombeiros", phone = "193", categoryId = 2),
                    EmergencyContact(name = "Polícia Civil", phone = "197", categoryId = 2),
                    EmergencyContact(name = "DETRAN", phone = "0800-888-2368", categoryId = 2),
                    EmergencyContact(name = "Polícia Rodoviária Estadual", phone = "198", categoryId = 2),

                    EmergencyContact(name = "Guarda Municipal", phone = "153", categoryId = 3),
                    EmergencyContact(name = "Defesa Civil", phone = "199", categoryId = 3),
                    EmergencyContact(name = "SAMU", phone = "192", categoryId = 3),
                    EmergencyContact(name = "Procon Municipal", phone = "151", categoryId = 3),
                    EmergencyContact(name = "CET", phone = "156", categoryId = 3)
                )

                if (contactCount == 0) {
                    emergencyContacts.forEach { emergencyContactDao.insertContact(it) }
                }
            }
        }
    }
}