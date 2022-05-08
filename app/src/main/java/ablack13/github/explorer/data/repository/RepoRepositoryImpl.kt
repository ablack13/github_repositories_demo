package ablack13.github.explorer.data.repository

import ablack13.github.explorer.domain.datasource.RepositoryDataSource
import ablack13.github.explorer.domain.model.RepositoryModel
import ablack13.github.explorer.domain.repository.RepoRepository

internal class RepoRepositoryImpl(
    private val repositoryDataSource: RepositoryDataSource
) : RepoRepository {

    override suspend fun getRemoteRepositories(
        query: String
    ): Result<List<RepositoryModel>> =
        getRemoteRepositoriesByPage(query = query, page = 1)

    override suspend fun getRemoteRepositoriesByPage(
        query: String,
        page: Int
    ): Result<List<RepositoryModel>> =
        kotlin.runCatching {
            if (query.isEmpty())
                emptyList()
            else
                repositoryDataSource.getRepositoriesList(
                    query = query,
                    page = page,
                    count = 10
                )
        }
}