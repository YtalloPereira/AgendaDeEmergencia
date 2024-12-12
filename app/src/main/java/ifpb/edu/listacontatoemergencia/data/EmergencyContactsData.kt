package ifpb.edu.listacontatoemergencia.data

import ifpb.edu.listacontatoemergencia.models.ContactCategory
import ifpb.edu.listacontatoemergencia.models.EmergencyContact

val emergencyContacts = listOf(
    ContactCategory(
        "Federal",
        listOf(
            EmergencyContact("Polícia Federal", "191"),
            EmergencyContact("Rodoviária Federal", "191")
        )
    ),
    ContactCategory(
        "Estadual",
        listOf(
            EmergencyContact("Polícia Militar", "190"),
            EmergencyContact("Bombeiros", "193")
        )
    ),
    ContactCategory(
        "Municipal",
        listOf(
            EmergencyContact("Guarda Municipal", "153"),
            EmergencyContact("Defesa Civil", "199")
        )
    )
)