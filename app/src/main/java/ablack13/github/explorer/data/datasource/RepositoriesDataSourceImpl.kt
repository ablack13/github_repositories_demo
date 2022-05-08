package ablack13.github.explorer.data.datasource

import ablack13.github.explorer.data.mapper.RepositoryModelMapper
import ablack13.github.explorer.data.net.WebService
import ablack13.github.explorer.domain.datasource.RepositoryDataSource
import ablack13.github.explorer.domain.model.RepositoryModel

internal class RepositoriesDataSourceImpl(
    private val webService: WebService,
    private val repositoryModelMapper: RepositoryModelMapper
) : RepositoryDataSource {

    //TODO need to handle error cases. Missed in a demo
    override suspend fun getRepositoriesList(
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