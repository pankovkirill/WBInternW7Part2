package com.example.wbinternw7part2.model.repository

interface Repository<T> {
    suspend fun getData(): T
}