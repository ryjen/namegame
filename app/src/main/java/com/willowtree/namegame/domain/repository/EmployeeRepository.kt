package com.willowtree.namegame.domain.repository

import com.willowtree.namegame.domain.model.Employee

interface EmployeeRepository {
    suspend fun employees(): Result<List<Employee>>
}
