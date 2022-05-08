package ablack13.github.explorer.domain.interactor

import ablack13.github.explorer.domain.model.RepositoryModel
import ablack13.github.explorer.domain.repository.RepoRepository

class RepositoriesLoadByPageGetInteractor(private val repoRepository: RepoRepository) {

    suspend fun exec(
        query: String,
        page: Int
    ): Result<List<RepositoryModel>> =
        repoRepository.getRemoteRepositoriesByPage(
            query = query,
            page = page
        )
}