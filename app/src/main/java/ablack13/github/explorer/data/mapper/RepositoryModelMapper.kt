package ablack13.github.explorer.data.mapper

import ablack13.github.explorer.data.net.dto.SearchRepositoriesResponse
import ablack13.github.explorer.domain.model.RepositoryModel

internal class RepositoryModelMapper {
    fun fromDto(dto: SearchRepositoriesResponse.Item): RepositoryModel =
        RepositoryModel(
            owner = dto.owner.login,
            ownerAvatar = dto.owner.avatarUrl,
            fullName = dto.fullName,
            id = dto.id,
            name = dto.name,
            nodeId = dto.nodeId,
            isPrivate = dto.isPrivate,
            visibility = dto.visibility,
        )
}