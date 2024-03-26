package data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Services(
    @SerialName("_id")
    val id: String?,
    @SerialName("image")
    val image: String?,
    @SerialName("inner_image")
    val innerImage: List<InnerImage?>?
)