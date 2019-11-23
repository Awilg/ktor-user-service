package com.yarn.services.data

import com.yarn.services.models.*
import org.litote.kmongo.coroutine.*

class UserDao(collection: CoroutineCollection<User>) : BaseDaoAsync<User>(collection)

