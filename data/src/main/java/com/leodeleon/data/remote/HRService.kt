package com.leodeleon.data.remote

import com.leodeleon.data.entities.Department
import com.leodeleon.data.entities.EmployeeDetailsDto
import com.leodeleon.data.entities.EmployeePage
import io.reactivex.Single
import retrofit2.http.FieldMap
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

private const val VERSION = "v1.0"

interface HRService {
    @GET("hr/$VERSION/employees/{id}")
    fun getEmployee(@Path("id") id: Int): Single<EmployeeDetailsDto>

    @GET("hr/$VERSION/departments/{id}")
    fun getDepartment(@Path("id") id: Int): Single<Department>

    @GET("hr/$VERSION/employees")
    fun getEmployees(@Query("offset") offset: Int): Single<EmployeePage>

    @POST("hr/$VERSION/employees/{id}")
    suspend fun updateEmployee(@Path("id") id: Int, @FieldMap hashMap: Map<String, String>)
}