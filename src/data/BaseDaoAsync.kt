package com.yarn.services.data

import com.yarn.services.com.yarn.services.data.*
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.*
import org.litote.kmongo.util.*

open class BaseDaoAsync<T : Any>(private val collection: CoroutineCollection<T>) : IBaseDaoAsync<T> {

    override suspend fun get(id: Any?): T? {
        return collection.findOne(collection::idValue eq id)
    }

    override suspend fun save(obj: T): Any? {
        return collection.save(obj).idValue
    }

    override suspend fun delete(id: Any?): Boolean {
        return collection.deleteOne(collection::idValue eq id).wasAcknowledged()
    }

}