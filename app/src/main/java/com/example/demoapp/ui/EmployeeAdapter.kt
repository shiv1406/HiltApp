package com.example.demoapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R
import com.example.demoapp.models.Employee
import kotlinx.android.synthetic.main.employee_item.view.*

class EmployeeAdapter: RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>(){

    private var fullList= listOf<Employee>()
    inner class EmployeeViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    private val diffCallback = object : DiffUtil.ItemCallback<Employee>(){
        override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this,diffCallback)

    fun submitList(list: List<Employee>) {
        fullList=list
        return differ.submitList(list)
    }
    fun filterList(constraint:String){
        val filteredList= arrayListOf<Employee>()
        filteredList.clear()
        if(constraint.isEmpty()){
            return differ.submitList(fullList)
        }
        for(singleItem in fullList) {
            val filterPattern = constraint.toLowerCase().trim()
            if(singleItem.employee_name?.toLowerCase()!!.contains(filterPattern)){
                filteredList.add(singleItem)
            }
        }
        return differ.submitList(filteredList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        return EmployeeViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(
                R.layout.employee_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {

        val item = differ.currentList[position]

        holder.itemView.apply {
            tvName.text = "${item.employee_name}"
            tvSalary.text = "Salary: Rs.${item.employee_salary}"
            tvAge.text = "Age: ${item.employee_age}"
        }

    }

}