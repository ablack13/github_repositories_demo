package ablack13.github.explorer.presentation.model

data class RepositoryUi(
    val id:String,
    val owner: String,
    val image: String?,
    val name: String,
    val isPrivate: Boolean
)