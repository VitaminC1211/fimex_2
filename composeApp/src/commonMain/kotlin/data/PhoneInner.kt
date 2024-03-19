package data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhoneInner(
    @SerialName("country_price")
    val countryPrice: List<CountryPrice?>?,
    @SerialName("id")
    val id: Int?,
    @SerialName("phone_type")
    val phoneType: String?
)