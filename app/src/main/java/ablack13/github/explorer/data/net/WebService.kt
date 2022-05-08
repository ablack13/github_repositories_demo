package ablack13.github.explorer.data.net

import ablack13.github.explorer.data.net.dto.SearchRepositoriesResponse
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

internal class WebService(restClient: RestClient) {
    companion object {
        private const val baseUrl = "https://api.github.com"

        fun searchReposPath(query: String, page: Int, count: Int): String =
            "/search/repositories?q=$query&page=$page&per_page=$count"
    }

    private val client = restClient.httpClient()

    suspend fun getRepositoriesList(
        searchQuery: String,
        page: Int,
        count: Int
    ): SearchRepositoriesResponse =
        client.get(
            url = Url(
                baseUrl + searchReposPath(
                    query = searchQuery,
                    page = page,
                    count = count
                )
            )
        ).body()
}