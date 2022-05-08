package ablack13.github.explorer.presentation.mapper

import ablack13.github.explorer.domain.model.RepositoryModel
import ablack13.github.explorer.presentation.model.RepositoryUi

class RepositoryUiMapper {
    fun fromModel(model: RepositoryModel): RepositoryUi =
        RepositoryUi(
            id = model.nodeId,
            owner = model.owner,
            image = model.ownerAvatar,
            name = model.name,
            isPrivate = model.isPrivate
        )
}