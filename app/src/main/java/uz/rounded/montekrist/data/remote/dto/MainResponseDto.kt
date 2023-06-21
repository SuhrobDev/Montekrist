package uz.rounded.montekrist.data.remote.dto

data class MainResponseDto<T>(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: T
)