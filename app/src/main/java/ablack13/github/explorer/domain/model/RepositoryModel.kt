package ablack13.github.explorer.domain.model

data class RepositoryModel(
    val owner:String,
    val ownerAvatar:String?,
    val fullName: String,
    val id: Int,
    val name: String,
    val nodeId: String,
    val isPrivate: Boolean,
    val visibility: String,
)