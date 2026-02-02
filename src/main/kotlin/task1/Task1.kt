package task1

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    repeat(1_000_000) {
        launch {
            println("+")
        }
    }
}