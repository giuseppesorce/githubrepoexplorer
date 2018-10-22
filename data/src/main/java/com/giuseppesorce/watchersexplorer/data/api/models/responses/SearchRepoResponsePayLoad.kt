package com.giuseppesorce.watchersexplorer.data.api.models.responses

import com.google.gson.annotations.Expose

/**
 * @author Giuseppe Sorce
 */
class SearchRepoResponsePayLoad {
    @Expose
    var items: List<ItemRepo>? = null
}

class ItemRepo {

    var id: Int? = null
    var watchers_count: Int? = null
    var name: String? = null
    var description: String? = null
    var language: String? = null
    var private: Boolean = false
    var forks_count: Int=0
    @Expose
    var owner: Owner? = null


}

class Owner {
    var id: Int? = null
    var login: String? = null
    var avatar_url: String? = null
    var repos_url: String? = null
}


