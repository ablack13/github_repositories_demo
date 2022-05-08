package ablack13.github.explorer.domain.datasource

import ablack13.github.explorer.domain.model.RepositoryModel

interface RepositoryDataSource {
    suspend fun getRepositoriesList(query:String, page:Int, count:Int): List<RepositoryModel>
}