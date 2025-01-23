package com.example.displaylistidcompose.network

import arrow.core.Either
import com.example.displaylistidcompose.model.HiringListItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HiringRepository @Inject constructor( private val hiringServiceApi: HiringServiceApi ){
    suspend fun getHiringList():Either<Throwable,List<HiringListItem>> =
        Either.catch{hiringServiceApi.getHiringList()}
}