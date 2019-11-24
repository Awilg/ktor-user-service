package com.yarn.services.models

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id
import org.litote.kmongo.newId

data class User(
    @BsonId val key : Id<User> = newId(),
    val name : String)