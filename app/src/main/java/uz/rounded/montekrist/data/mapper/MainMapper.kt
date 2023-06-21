package uz.rounded.montekrist.data.mapper

import uz.rounded.montekrist.data.remote.dto.people.PeopleDto
import uz.rounded.montekrist.data.remote.dto.starship.StarshipDto
import uz.rounded.montekrist.domain.model.people.PeopleModel
import uz.rounded.montekrist.domain.model.starship.Starship
import uz.rounded.montekrist.domain.model.starship.StarshipModel

fun StarshipDto.toModel(): StarshipModel {
    return StarshipModel(
        films = films,
        model = model,
        name = name,
        passengers = passengers,
        starship_class = starship_class,
        url = url,
        cost_in_credits = cost_in_credits
    )
}

fun PeopleDto.toModel(): PeopleModel {
    return PeopleModel(
        birth_year = birth_year,
        films = films,
        gender = gender,
        height = height,
        name = name,
        starships = starships,
        url = url,
        vehicles = vehicles
    )
}

fun StarshipModel.toStarship(): Starship {
    return Starship(
        id = _id.toInt(),
        model = model,
        name = name,
        passengers = passengers,
        starship_class = starship_class,
        url = url,
        cost_in_credits = cost_in_credits
    )
}