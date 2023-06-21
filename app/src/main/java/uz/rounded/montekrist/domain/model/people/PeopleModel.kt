package uz.rounded.montekrist.domain.model.people

data class PeopleModel(
    val birth_year: String,
    val films: List<String>,
    val gender: String,
    val height: String,
    val name: String,
    val starships: List<String>,
    val url: String,
    val vehicles: List<String>
)