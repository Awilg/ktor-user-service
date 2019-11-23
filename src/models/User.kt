package com.yarn.services.models

import org.bson.codecs.pojo.annotations.*
import org.litote.kmongo.*

data class User(
    @BsonId val key : Id<User> = newId(),
    val name : String)