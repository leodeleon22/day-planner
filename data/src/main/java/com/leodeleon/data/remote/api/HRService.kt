package com.leodeleon.data.remote.api

import com.leodeleon.data.entities.Department
import com.leodeleon.data.entities.EmployeeDetails
import com.leodeleon.data.entities.EmployeeItemDto
import com.leodeleon.data.remote.response.Envelope
import com.leodeleon.data.remote.response.Page
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

private const val VERSION = "v1.0"

interface HRService {
    @GET("hr/$VERSION/departments/{id}")
    fun getDepartment(@Path("id") id: Int): Single<Envelope<Department>>

    @GET("hr/$VERSION/employees")
    fun getEmployees(@Query("offset") offset: Int): Single<Page<List<EmployeeItemDto>>>

    @PUT("hr/$VERSION/employees/{id}")
    suspend fun updateEmployee(@Path("id") id: Int, @Body hashMap: Map<String, String>): Response<Unit>

    @GET("hr/$VERSION/employees/{id}")
    suspend fun getEmployee(@Path("id") id: Int): Response<Envelope<EmployeeDetails>>
}