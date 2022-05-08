package ablack13.github.explorer.presentation.common

import ablack13.github.explorer.domain.util.DispatcherProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.*

interface BaseIntent
interface BaseState
interface BaseAction

abstract class BaseViewModel<I : BaseIntent, S : BaseState, A : BaseAction>(
    protected val dispatcherProvider: DispatcherProvider,
    state: S
) : ViewModel() {
    private val viewModelJob = SupervisorJob()
    protected val vmScope: CoroutineScope =
        CoroutineScope(viewModelScope.coroutineContext + viewModelJob)

    private val parentJob = Job()
    private val _stateFlow = MutableStateFlow(value = state)
    private val _actionFlow = MutableSharedFlow<A>()

    val stateFlow = _stateFlow.asStateFlow()
    val actionFlow = _actionFlow.asSharedFlow()

    abstract fun obtainIntent(intent: I)

    protected fun updateState(op: S.() -> S) {
        _stateFlow.update(op)
    }

    protected suspend fun emitAction(action: A) {
        _actionFlow.emit(action)
    }

    protected fun getState(): S =
        _stateFlow.value

    public override fun onCleared() {
        super.onCleared()
        viewModelJob.cancelChildren()
    }
}