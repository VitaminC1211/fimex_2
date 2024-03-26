package data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountryPrice(
    @SerialName("id")
    val id: Int?,
    @SerialName("price")
    val price: String?
)