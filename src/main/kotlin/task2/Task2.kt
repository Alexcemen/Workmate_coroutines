package task2

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

fun <T> Flow<T>.throttleFirst(windowMills: Long): Flow<T> = flow {
    var lastEmitTime = 0L

    collect { value ->
        val now = System.currentTimeMillis()
        if (now - lastEmitTime >= windowMills) {
            lastEmitTime = now
            emit(value)
        }
    }
}

// аналог throttleLatest в Flow: Flow<T>.sample(time: Long)