package data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Register(
    @SerialName("id")
    var id: Int?,
    @SerialName("Name")
    var name: String?,
    @SerialName("Gmail")
    var gmail: String?,
    @SerialName("Password")
    var password: String?
)
