package com.giuseppesorce.watchersexplorer.domain.mappers

import com.giuseppesorce.watchersexplorer.data.api.models.Repo
import com.giuseppesorce.watchersexplorer.data.api.models.RepoWatcher
import com.giuseppesorce.watchersexplorer.data.api.models.responses.SearchRepoResponsePayLoad
import com.giuseppesorce.watchersexplorer.data.api.models.responses.SubscriberResponse
import javax.inject.Inject

/**
 * @author Giuseppe Sorce
 *
 * <p>this service class map and tranform model received to another useful model
 */
class SearchMapper @Inject constructor() {

    /**
     * to transform server response to a list of Repo model
     */
    fun getRepo(response: SearchRepoResponsePayLoad): List<Repo> {
        return response.items?.let {

            it.map {
                Repo(
                    it.id ?: 0,
                    it.watchers_count ?: 0,
                    it.name,
                    it.description,
                    it.language ?: "",

                    it.private,
                    it.forks_count,
                    it.owner?.id ?: 0,
                    it.owner?.login,
                    it.owner?.avatar_url,
                    it.owner?.repos_url,
                    it.owner?.login
                )
            }

        } ?: emptyList<Repo>()

    }

    /**
     * to transform watcher server reponse to a list of RepoWarcher
     */
    fun getWatchers(serverWatchers: List<SubscriberResponse>?): List<RepoWatcher>? {

        return serverWatchers?.let {
            it.map {
                RepoWatcher(
                    it.login ?: "", it.avatar_url ?: "", it.id
                )
            }
        } ?: emptyList()


    }

}

