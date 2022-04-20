package com.example.employeedirectory.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.employeedirectory.R
import com.example.employeedirectory.data.entities.Employee
import com.example.employeedirectory.data.viewmodel.EmployeeViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mEmployeeViewModel: EmployeeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mEmployeeViewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)

        view.etUpdateFirstName.setText(args.currentEmployee.firstName)
        view.etUpdateLastName.setText(args.currentEmployee.lastName)
        view.etUpdateAge.setText(args.currentEmployee.age.toString())
        view.etUpdateProfession.setText(args.currentEmployee.profession)

        view.btnUpdate.setOnClickListener {
            updateItem()
        }
        setHasOptionsMenu(true)
        return view
    }

    private fun updateItem() {
        val firstName = etUpdateFirstName.text.toString()
        val lastName = etUpdateLastName.text.toString()
        val age = Integer.parseInt(etUpdateAge.text.toString())
        val profession = etUpdateProfession.text.toString()

        if (inputCheck(firstName, lastName, etUpdateAge.text , profession)) {
            val updatedEmployee = Employee(args.currentEmployee.id, firstName, lastName, age, profession)
            mEmployeeViewModel.updateEmployee(updatedEmployee)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable, profession: String): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty() && TextUtils.isEmpty(profession))
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteEmployee()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteEmployee() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mEmployeeViewModel.deleteEmployee(args.currentEmployee)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentEmployee.firstName}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentEmployee.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentEmployee.firstName}?")
        builder.create().show()
    }
}