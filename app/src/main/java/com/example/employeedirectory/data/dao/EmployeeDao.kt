package com.example.employeedirectory.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.employeedirectory.data.entities.Employee

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addEmployee(employee: Employee)

    @Update
    fun updateEmployee(employee: Employee)

    @Query("SELECT * FROM employee_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Employee>>

    @Delete
    fun deleteEmployee(employee: Employee)


    @Query("DELETE FROM employee_table")
    fun deleteAllEmployees()
}