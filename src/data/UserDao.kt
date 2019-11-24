package com.yarn.services.data

import com.yarn.services.models.User
import org.litote.kmongo.coroutine.CoroutineCollection

class UserDao(collection: CoroutineCollection<User>) : BaseDaoAsync<User>(collection)

