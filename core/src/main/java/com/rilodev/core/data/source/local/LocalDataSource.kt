package com.rilodev.core.data.source.local

import com.rilodev.core.data.source.local.room.AppDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val appDao: AppDao)