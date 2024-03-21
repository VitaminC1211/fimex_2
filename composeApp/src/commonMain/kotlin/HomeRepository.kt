
import apiClient.httpClient
import data.Login
import data.Register
import data.Services
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class HomeRepository {

    //Getting all of the items from backend
    suspend fun getServiceItem(): List<Services> {
        val response = httpClient.get("http://103.35.189.138:3005")
        return response.body()
    }

    fun getService() = flow {
        emit(getServiceItem())
    }

    //Login
    suspend fun sendLoginDataToBackend(user: Login){

    }

    //Register
    suspend fun sendRegisterDataToBackend(user: Register){

        val jsonBody = Json.encodeToString(user)

        println("JSON Body1: $jsonBody")

        val client = HttpClient(CIO)
        val response: HttpResponse = client.post("http://103.35.189.138:3005/api/data") {
            contentType(ContentType.Application.Json)
            setBody(jsonBody)

            println("JSON Body2: $jsonBody")
        }
    }
}