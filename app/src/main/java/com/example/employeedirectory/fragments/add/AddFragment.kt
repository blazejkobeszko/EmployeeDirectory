package com.example.employeedirectory.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.employeedirectory.R
import com.example.employeedirectory.data.entities.Employee
import com.example.employeedirectory.data.viewmodel.EmployeeViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mEmployeeViewModel: EmployeeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_add, container, false)

        mEmployeeViewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)
        view.btnAdd.setOnClickListener{
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {
        val firstName= etFirstName.text.toString()
        val lastName = etLastName.text.toString()
        val age = etAge.text
        val profession = etProfession.text.toString()

        if(inputCheck(firstName,lastName, age, profession)){
            val employee = Employee(0, firstName, lastName, Integer.parseInt(age.toString()), profession)
            mEmployeeViewModel.addEmployee(employee)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }
    private fun inputCheck(firstName: String, lastName: String, age: Editable, profession: String): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty() && TextUtils.isEmpty(profession))

    }
}