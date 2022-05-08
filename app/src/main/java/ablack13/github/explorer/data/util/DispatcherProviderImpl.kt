package ablack13.github.explorer.data.util

import ablack13.github.explorer.domain.util.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher

class DispatcherProviderImpl(
    override val default: CoroutineDispatcher,
    override val main: CoroutineDispatcher,
    override val io: CoroutineDispatcher
) :DispatcherProvider