package task3

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL
import javax.net.ssl.HttpsURLConnection

const val URL_STRING = "https://api.frankfurter.app/latest?from=GBP&to=USD&amount=10"

suspend fun main() {
    val scope = CoroutineScope(Dispatchers.IO)
    val job = scope.launch {
        val result = loadUrlAsString(URL_STRING)
        withContext(Dispatchers.Main) {
            println(result)
        }
    }
    job.join()
}

fun loadUrlAsString(url: String): String {
    val connection = URL(url).openConnection() as HttpsURLConnection
    return try {
        connection.inputStream.bufferedReader().use {
            it.readText()
        }
    } finally {
        connection.disconnect()
    }
}