package ablack13.github.explorer.ui.composable

import ablack13.github.explorer.presentation.common.BaseAction
import ablack13.github.explorer.presentation.common.BaseState
import ablack13.github.explorer.presentation.common.BaseViewModel
import android.annotation.SuppressLint
import android.view.View
import androidx.compose.runtime.*
import kotlinx.coroutines.CoroutineScope

@Composable
fun OnAppear(block: suspend CoroutineScope.() -> Unit) {
    LaunchedEffect(Unit, block)
}

@Composable
fun OnDisappear(op: () -> Unit) {
    DisposableEffect(Unit) {
        onDispose { op() }
    }
}

@Composable
fun <T : BaseState> BaseViewModel<*, T, *>.getViewState(): T =
    stateFlow.collectAsState().value

@SuppressLint("ComposableNaming")
@Composable
fun <T : BaseAction> BaseViewModel<*, *, T>.observeActionState(block: suspend (action: T) -> Unit) {
    val action = actionFlow.collectAsState(null).value
    LaunchedEffect(action) {
        if (action != null)
            block.invoke(action)
    }
}