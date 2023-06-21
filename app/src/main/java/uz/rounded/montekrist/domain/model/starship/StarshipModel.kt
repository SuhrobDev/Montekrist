package uz.rounded.montekrist.domain.model.starship


data class StarshipModel(
    val _id: Int = 0,
    val films: List<String>? = emptyList(),
    val model: String,
    val name: String,
    val passengers: String,
    val starship_class: String,
    val url: String,
    val cost_in_credits: String,
)