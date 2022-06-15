package com.example.wbinternw7part2.model.data

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/all.json")
    fun getDataAsync(): Deferred<List<DataModel>>
}