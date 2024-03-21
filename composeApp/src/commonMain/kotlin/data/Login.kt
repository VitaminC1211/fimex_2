package data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Login (
    @SerialName("id")
    var id: Int?,
    @SerialName("Gmail")
    var gmail: String?,
    @SerialName("Password")
    var password: String?
)
