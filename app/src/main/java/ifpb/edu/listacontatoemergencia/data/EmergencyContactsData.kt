package ifpb.edu.listacontatoemergencia.data

import ifpb.edu.listacontatoemergencia.models.ContactCategory
import ifpb.edu.listacontatoemergencia.models.EmergencyContact

val emergencyContacts = listOf(
    ContactCategory(
        "Federal",
        listOf(
            EmergencyContact("Polícia Federal", "194"),
            EmergencyContact("Rodoviária Federal", "191"),
            EmergencyContact("IBAMA (Instituto Brasileiro do Meio Ambiente)", "0800-618080"),
            EmergencyContact("Receita Federal", "146"),
            EmergencyContact("Aeronáutica (Polícia)", "190")
        )
    ),
    ContactCategory(
        "Estadual",
        listOf(
            EmergencyContact("Polícia Militar", "190"),
            EmergencyContact("Bombeiros", "193"),
            EmergencyContact("Polícia Civil", "197"),
            EmergencyContact("DETRAN (Departamento Estadual de Trânsito)", "0800-888-2368"),
            EmergencyContact("Polícia Rodoviária Estadual", "198")
        )
    ),
    ContactCategory(
        "Municipal",
        listOf(
            EmergencyContact("Guarda Municipal", "153"),
            EmergencyContact("Defesa Civil", "199"),
            EmergencyContact("SAMU (Serviço de Atendimento Móvel de Urgência)", "192"),
            EmergencyContact("Procon Municipal", "151"),
            EmergencyContact("CET (Companhia de Engenharia de Tráfego)", "156")
        )
    )
)
