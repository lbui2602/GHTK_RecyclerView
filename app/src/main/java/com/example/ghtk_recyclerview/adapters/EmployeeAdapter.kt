package com.example.ghtk_recyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ghtk_recyclerview.databinding.LayoutEmployeeItemBinding
import com.example.ghtk_recyclerview.databinding.LayoutEmployeeMarBinding
import com.example.ghtk_recyclerview.databinding.LayoutEmployeeNsBinding
import com.example.ghtk_recyclerview.models.Employee

class EmployeeAdapter(private var list: MutableList<Employee>) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    class EmployeeViewHolder(val itemBinding: LayoutEmployeeItemBinding) : RecyclerView.ViewHolder(itemBinding.root)
    class EmployeeViewHolderMar(val itemBindingMar: LayoutEmployeeMarBinding) : RecyclerView.ViewHolder(itemBindingMar.root)
    class EmployeeViewHolderNS(val itemBindingNS: LayoutEmployeeNsBinding) : RecyclerView.ViewHolder(itemBindingNS.root)
    companion object {
        private const val IT = 0
        private const val MAR = 1
        private const val NS = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (list?.get(position)?.phong.toString()) {
            "IT" -> IT
            "Marketing" -> MAR
            "Nhân sự" -> NS
            else -> throw IllegalArgumentException("Invalid type of data $position")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            IT -> {
                EmployeeViewHolder(LayoutEmployeeItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            MAR -> {
                EmployeeViewHolderMar(LayoutEmployeeMarBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            NS -> {
                EmployeeViewHolderNS(LayoutEmployeeNsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentEmployee = list?.get(position)
        when (holder.itemViewType) {
            IT -> {
                (holder as EmployeeViewHolder).itemBinding.apply {
                    holder.itemBinding.tvName.text = currentEmployee!!.name
                    holder.itemBinding.tvMaNV.text = currentEmployee.mnv
                    holder.itemBinding.tvSalary.text = currentEmployee.salary.toString()
                    holder.itemBinding.tvPhong.text = currentEmployee.phong
                    holder.itemBinding.tvPosition.text = currentEmployee.position
                }
            }
            MAR -> {
                (holder as EmployeeViewHolderMar).itemBindingMar.apply {
                    holder.itemBindingMar.tvName.text = currentEmployee!!.name
                    holder.itemBindingMar.tvMaNV.text = currentEmployee.mnv
                    holder.itemBindingMar.tvSalary.text = currentEmployee.salary.toString()
                    holder.itemBindingMar.tvPhong.text = currentEmployee.phong
                    holder.itemBindingMar.tvPosition.text = currentEmployee.position
                }
            }
            NS -> {
                (holder as EmployeeViewHolderNS).itemBindingNS.apply {
                    holder.itemBindingNS.tvName.text = currentEmployee!!.name
                    holder.itemBindingNS.tvMaNV.text = currentEmployee.mnv
                    holder.itemBindingNS.tvSalary.text = currentEmployee.salary.toString()
                    holder.itemBindingNS.tvPhong.text = currentEmployee.phong
                    holder.itemBindingNS.tvPosition.text = currentEmployee.position
                }
            }
        }
    }
    override fun getItemCount(): Int {
        return list?.size!!
    }

    fun updateList(newList: List<Employee>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}