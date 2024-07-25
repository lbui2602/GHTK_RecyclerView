package com.example.ghtk_recyclerview.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ghtk_recyclerview.models.Employee

class EmployeeViewModel : ViewModel() {
    private val _list = MutableLiveData<List<Employee>>()
    val list: LiveData<List<Employee>> = _list

    init {
        _list.value = mutableListOf()
    }

    fun fakeData() {
        val departments = listOf("Nhân sự", "Marketing", "IT")
        val currentList = _list.value?.toMutableList() ?: mutableListOf()
        for (i in 1..10) {
            val mnv = "ABC$i"
            val name = "Bùi Đức Lương $i"
            val position = "Position $i"
            val salary = (30000..100000).random()
            val phong = departments.random()
            val employee = Employee(mnv, name, position, salary, phong)
            currentList.add(employee)
        }
        _list.value = currentList
    }
    fun refresh(){
        _list.value= mutableListOf()
        fakeData()
    }
    fun loadMore(currentSize: Int, nextSize: Int) {
        val departments = listOf("Nhân sự", "Marketing", "IT")
        val currentList = _list.value?.toMutableList() ?: mutableListOf()
        for (i in currentSize until currentSize + nextSize) {
            val mnv = "ABC$i"
            val name = "Bùi Đức Lương $i"
            val position = "Position $i"
            val salary = (30000..100000).random()
            val phong = departments.random()
            val employee = Employee(mnv, name, position, salary, phong)
            Log.d("test",employee.toString())
            currentList.add(employee)
        }
        _list.value = currentList
    }
}
