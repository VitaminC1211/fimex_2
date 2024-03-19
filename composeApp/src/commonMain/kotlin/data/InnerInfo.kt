package data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InnerInfo(
    @SerialName("id")
    val id: Int?,
    @SerialName("phone_inner")
    val phoneInner: List<PhoneInner?>?,
    @SerialName("text")
    val text: String?
)