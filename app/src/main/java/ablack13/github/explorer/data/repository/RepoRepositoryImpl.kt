package ablack13.github.explorer.data.repository

import ablack13.github.explorer.data.mapper.RepositoryModelMapper
import ablack13.github.explorer.data.net.WebService
import ablack13.github.explorer.domain.model.RepositoryModel
import ablack13.github.explorer.domain.repository.RepoRepository

internal class RepoRepositoryImpl(
    private val webService: WebService,
    private val repositoryModelMapper: RepositoryModelMapper
) : RepoRepository {

    override suspend fun getRemoteRepositories(
        query: String
    ): Result<List<RepositoryModel>> =
        getRemoteRepositoriesByPage(
            query = query,
            page = 1,
            count = 10
        )

    override suspend fun getRemoteRepositoriesByPage(
        query: String,
        page: Int,
        count: Int
    ): Result<List<RepositoryModel>> =
        kotlin.runCatching {
            if (query.isEmpty())
                emptyList()
            else
                getRemoteRepositories(
                    query = query,
                    page = page,
                    count = count
                )
        }

    private suspend fun getRemoteRepositories(
        query: String,
        page: Int,
        count: Int
    ): List<RepositoryModel> =
        webService.getRepositoriesList(
            searchQuery = query,
            page = page,
            count = count
        ).items.map { item -> repositoryModelMapper.fromDto(dto = item) }

}