package data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Login (
    @SerialName("email")
    var email: String?,
    @SerialName("password")
    var password: String?
)
