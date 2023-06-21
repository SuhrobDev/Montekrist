package uz.rounded.montekrist.data.remote

data class APIErrorResponse(
    val message: String,
    val statusCode: Int,
    val success: Boolean
)
