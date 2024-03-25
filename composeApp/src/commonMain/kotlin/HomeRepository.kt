
import apiClient.httpClient
import data.Login
import data.Register
import data.Services
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
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
        val response = httpClient.post("http://103.35.189.138:3005/api/services/getAll")
        return response.body()
    }

    fun getService() = flow {
        emit(getServiceItem())
    }

    //Login
    suspend fun sendLoginDataToBackend(login: Login): String{
        val jsonBody = Json.encodeToString(login)

        val client = HttpClient(CIO)
        val response: HttpResponse = client.post("http://103.35.189.138:3005/api/users/login") {
            contentType(ContentType.Application.Json)
            setBody(jsonBody)
        }
        val res = response.body<String>()
        println("LOGINRES:$res");
        return res
    }


    //Register
    suspend fun sendRegisterDataToBackend(user: Register) : String{

        val jsonBody = Json.encodeToString(user)

        val client = HttpClient(CIO)
        val response: HttpResponse = client.post("http://103.35.189.138:3005/api/users/register") {
            contentType(ContentType.Application.Json)
            setBody(jsonBody)
        }
        val res = response.body<String>()

        println("_____response from the server_____:$res")

        return res
    }
}