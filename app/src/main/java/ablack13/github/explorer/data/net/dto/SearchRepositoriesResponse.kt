package ablack13.github.explorer.data.net.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchRepositoriesResponse(
    @SerialName("incomplete_results")
    val incompleteResults: Boolean,
    @SerialName("items")
    val items: List<Item>,
    @SerialName("total_count")
    val totalCount: Int
) {
    @Serializable
    data class Item(
        @SerialName("full_name")
        val fullName: String,
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("node_id")
        val nodeId: String,
        @SerialName("owner")
        val owner: Owner,
        @SerialName("private")
        val isPrivate: Boolean,
        @SerialName("url")
        val url: String,
        @SerialName("visibility")
        val visibility: String,
    ) {
        @Serializable
        data class Owner(
            @SerialName("avatar_url")
            val avatarUrl: String,
            @SerialName("login")
            val login: String,
        )
    }
}