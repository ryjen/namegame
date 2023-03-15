package com.willowtree.namegame.domain.repository

import com.willowtree.namegame.domain.model.Employee

interface EmployeeRepository {
    suspend fun all(): List<Employee>
}
