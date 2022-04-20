package com.example.employeedirectory.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.employeedirectory.data.database.EmployeeDatabase
import com.example.employeedirectory.data.entities.Employee
import com.example.employeedirectory.data.repository.EmployeeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmployeeViewModel (application: Application) : AndroidViewModel(application){
    val readAllData: LiveData<List<Employee>>
    private val repository: EmployeeRepository

    init {
        val employeeDao = EmployeeDatabase.getDatabase(application).employeeDao()
        repository = EmployeeRepository(employeeDao)
        readAllData = repository.readAllData
    }

    fun  addEmployee(employee: Employee){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addEmployee(employee)
        }
    }

    fun updateEmployee(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateEmployee(employee)
        }
    }

    fun  deleteEmployee(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteEmployee(employee)
        }
    }

    fun  deleteAllEmployees() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllEmployees()
        }
    }
}