package data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InnerImage(
    @SerialName("description")
    val description: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("image")
    val image: String?,
    @SerialName("inner_info")
    val innerInfo: List<InnerInfo?>?
)