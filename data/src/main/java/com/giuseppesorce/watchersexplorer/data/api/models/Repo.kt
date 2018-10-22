package com.giuseppesorce.watchersexplorer.data.api.models

/**
 * @author Giuseppe Sorce
 */
data class Repo(
    var id: Int,
    var watchers_count: Int = 0,
    var name: String? = null,
    var description: String? = null,
    var language: String? = null,
    var private: Boolean = false,
    var forks_count: Int,
    var ownerId: Int,
    var login: String? = null,
    var avatar_url: String? = null,
    var repos_url: String? = null,
    var nameOwner: String? = null
)
