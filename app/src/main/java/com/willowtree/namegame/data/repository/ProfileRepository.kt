package com.willowtree.namegame.data.repository

import com.willowtree.namegame.data.model.Profile
import com.willowtree.namegame.data.remote.source.RemoteSource
import com.willowtree.namegame.domain.model.Employee
import com.willowtree.namegame.domain.repository.EmployeeRepository

class ProfileRepository(
    private val remoteSource: RemoteSource,
) : EmployeeRepository {
    override suspend fun all() = remoteSource.employees().toDomain()
}

fun List<Profile>.toDomain(): List<Employee> = filter { it.headshot.url != null }.map {
    Employee(
        id = it.id,
        name = Employee.Name(
            firstName = it.firstName,
            lastName = it.lastName
        ),
        headshot = it.headshot.toDomain()
    )
}

fun Profile.Headshot.toDomain() = Employee.Headshot(
    url = url ?: "",
    alt = alt
)
