package uz.rounded.montekrist.data.remote.dto.people

import com.google.gson.annotations.SerializedName

data class PeopleResponseDto(
    @SerializedName("results")
    val peoples: List<PeopleDto>
)
