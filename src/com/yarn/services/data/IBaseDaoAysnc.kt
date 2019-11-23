package com.yarn.services.com.yarn.services.data

interface IBaseDaoAsync<T> {

    suspend fun get(id: Any?) : T?
    suspend fun save(obj : T) : Any?
    suspend fun delete(id: Any?) : Boolean
}
