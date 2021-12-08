package com.example.talenttj.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.LinearLayout.HORIZONTAL
import android.widget.LinearLayout.VERTICAL
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.talenttj.*
import com.example.talenttj.viewmodel.MainViewModel
import com.example.talenttj.ui.adapters.UserAdapter
import com.example.talenttj.databinding.FragmentSearchBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
     private val  viewModel: MainViewModel by viewModels()

    private var userAdapter = UserAdapter {
        val bundle = Bundle()
        bundle.putInt(UserFragment.INTENT_EXTRA, it)
        (activity
            ?.supportFragmentManager
            ?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment)
            .navController
            .navigate(R.id.action_searchFragment_to_userFragment, bundle)
    }

    // binding
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = _binding ?: throw RuntimeException("error with binding SearchFragment.kt !!!")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.spinnerProfession.adapter = viewModel.getProfessionSpinnerAdapter()
        binding.spinnerSkill.adapter = viewModel.getSkillSpinnerAdapter()

        with(binding)
        {
            spinnerProfession.onItemSelectedListener = getSpinnerListener()
            spinnerSkill.onItemSelectedListener = getSpinnerListener()
            spinnerCity.onItemSelectedListener = getSpinnerListener()
        }

        setupRecyclerView()
        registerLiveData()
        onSearch()
    }

    private fun setupRecyclerView() = binding.recycler.apply {
        layoutManager = LinearLayoutManager(context)
        adapter = userAdapter
        addItemDecoration(DividerItemDecoration(context, VERTICAL))
        addItemDecoration(DividerItemDecoration(context, HORIZONTAL))
        hasFixedSize()
    }


    private fun registerLiveData() {
        viewModel.searchLiveData.observe(this) {
            // данные пришли
            CoroutineScope(Dispatchers.IO).launch {
                userAdapter.submitData(it)
            }
        }
    }

    private fun onSearch(){
        viewModel.searchUser(
            binding.spinnerProfession.selectedItem.toString(),
            binding.spinnerSkill.selectedItemPosition+1,
            binding.spinnerCity.selectedItem.toString()
        )
    }

    private fun getSpinnerListener() = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            onSearch()
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {}
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}