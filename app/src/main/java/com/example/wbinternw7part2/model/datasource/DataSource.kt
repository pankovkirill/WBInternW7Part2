package com.example.wbinternw7part2.model.datasource

interface DataSource<T> {
    suspend fun getData(): T
}