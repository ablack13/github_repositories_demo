package ablack13.github.explorer.domain.interactor

import ablack13.github.explorer.domain.model.RepositoryModel
import ablack13.github.explorer.domain.repository.RepoRepository

class RepositoriesLoadGetInteractor(private val repoRepository: RepoRepository) {

    suspend fun exec(query: String): Result<List<RepositoryModel>> =
        repoRepository.getRemoteRepositories(query = query)
}