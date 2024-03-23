
import data.Services
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private val _services = MutableStateFlow<List<Services>>(listOf())
    val service = _services.asStateFlow()

    val homeRepository = HomeRepository()

    init {
        viewModelScope.launch {
            homeRepository.getService().collect{services ->
                _services.update { it + services }
            }
        }
    }
}