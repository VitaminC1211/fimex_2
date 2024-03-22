package data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Register(
    @SerialName("name")
    var name: String?,
    @SerialName("email")
    var gmail: String?,
    @SerialName("password")
    var password: String?
)
