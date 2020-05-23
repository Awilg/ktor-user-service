package com.yarn.services.models

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id
import org.litote.kmongo.newId
import java.time.Instant

data class User(
    @BsonId val id : Id<User> = newId(),
    val name : String,
    val activeTreasureHunts: List<String>?,
    val completedTreasureHunts: List<String>?,
    val avatarUrl: String?,
    val createdAt: String = Instant.now().toEpochMilli().toString()
)