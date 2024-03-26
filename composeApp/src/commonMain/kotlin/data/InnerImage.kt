package data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InnerImage(
    @SerialName("description")
    val description: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("images")
    val images: String?,
    @SerialName("inner_info")
    val innerInfo: List<InnerInfo?>?
)