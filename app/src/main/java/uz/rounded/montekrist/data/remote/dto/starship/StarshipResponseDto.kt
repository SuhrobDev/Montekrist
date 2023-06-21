package uz.rounded.montekrist.data.remote.dto.starship

import com.google.gson.annotations.SerializedName

data class StarshipResponseDto(
    @SerializedName("results")
    val starship: List<StarshipDto>

)
