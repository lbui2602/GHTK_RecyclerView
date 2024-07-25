package com.example.ghtk_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ghtk_recyclerview.adapters.EmployeeAdapter
import com.example.ghtk_recyclerview.databinding.ActivityMainBinding
import com.example.ghtk_recyclerview.models.Employee
import com.example.ghtk_recyclerview.viewmodels.EmployeeViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: EmployeeAdapter
    private lateinit var binding: ActivityMainBinding
    private val employeeViewModel: EmployeeViewModel by lazy {
        ViewModelProvider(this).get(EmployeeViewModel::class.java)
    }
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = EmployeeAdapter(mutableListOf())
        binding.rcv.layoutManager = LinearLayoutManager(this)
        binding.rcv.adapter = adapter

        employeeViewModel.fakeData()

        binding.swipeRefreshLayout.setOnRefreshListener {
            employeeViewModel.refresh()
        }

        binding.rcv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                    isLoading = true
                    employeeViewModel.loadMore(adapter.itemCount, adapter.itemCount + 10)
                }
            }
        })

        employeeViewModel.list.observe(this, Observer { list ->
            list?.let {
                adapter.updateList(it)
                isLoading = false
                binding.swipeRefreshLayout.isRefreshing = false
            }
        })
    }
}
