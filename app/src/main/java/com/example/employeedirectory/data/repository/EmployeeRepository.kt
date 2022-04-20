package com.example.employeedirectory.data.repository

import androidx.lifecycle.LiveData
import com.example.employeedirectory.data.dao.EmployeeDao
import com.example.employeedirectory.data.entities.Employee

class EmployeeRepository (private  val employeeDao: EmployeeDao) {
    val readAllData : LiveData<List<Employee>> = employeeDao.readAllData()

    suspend fun addEmployee(employee: Employee){
        employeeDao.addEmployee(employee)
    }

    suspend fun updateEmployee(employee: Employee) {
        employeeDao.updateEmployee(employee)
    }

    suspend fun deleteEmployee(employee: Employee) {
        employeeDao.deleteEmployee(employee)
    }

    suspend fun deleteAllEmployees(){
        employeeDao.deleteAllEmployees()
    }
}