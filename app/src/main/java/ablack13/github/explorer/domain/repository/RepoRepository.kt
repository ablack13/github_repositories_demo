package ablack13.github.explorer.domain.repository

import ablack13.github.explorer.domain.model.RepositoryModel

interface RepoRepository {
    suspend fun getRemoteRepositories(query: String): Result<List<RepositoryModel>>
    suspend fun getRemoteRepositoriesByPage(query: String, page: Int,count:Int): Result<List<RepositoryModel>>
}