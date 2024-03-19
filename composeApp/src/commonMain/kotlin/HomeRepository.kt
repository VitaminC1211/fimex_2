import apiClient.httpClient
import data.Services
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.flow

class HomeRepository {

    suspend fun getServiceItem(): List<Services>{
        val response = httpClient.get("http://103.35.189.138:3005")
        return response.body()
    }

    fun getService() = flow{
        emit(getServiceItem())
    }
}