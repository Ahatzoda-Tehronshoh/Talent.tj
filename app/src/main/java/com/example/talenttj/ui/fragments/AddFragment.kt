package com.example.talenttj.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.example.talenttj.data.ProfessionAndSkill
import com.example.talenttj.R
import com.example.talenttj.data.room.entities.User
import com.example.talenttj.databinding.FragmentAddBinding
import com.example.talenttj.viewmodel.MainViewModel
import java.lang.RuntimeException

class AddFragment : Fragment() {
    val viewModel: MainViewModel by viewModels()
    // binding
    private var _binding: FragmentAddBinding? = null
    private val binding: FragmentAddBinding
        get()= _binding ?: throw RuntimeException("layout add_fragment doesn't working!!!!!")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addButton.setOnClickListener {
            with(binding) {
                val user = User(
                    0,
                    editText.text.toString(),
                    editTextSurname.text.toString(),
                    editTextEmail.text.toString(),
                    editTextCity.text.toString(),
                    editTextCountry.text.toString(),
                    editTextBirthDay.text.toString(),
                    educationSpinner.selectedItem.toString(),
                    professionSpinner.selectedItemPosition,
                    workingStatusSpinner.selectedItemPosition == 0,
                    editTextUserDesc.text.toString(),
                    editTextPhone.text.toString(),
                )
                user.skills = listOf(1, 2, 5, 10)
                viewModel.addUser(user)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}