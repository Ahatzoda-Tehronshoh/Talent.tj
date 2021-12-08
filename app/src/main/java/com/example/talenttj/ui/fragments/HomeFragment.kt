package com.example.talenttj.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout.HORIZONTAL
import android.widget.LinearLayout.VERTICAL
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.map
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.talenttj.*
import com.example.talenttj.viewmodel.MainViewModel
import com.example.talenttj.ui.adapters.UserAdapter
import com.example.talenttj.databinding.FragmentHomeBinding
import com.example.talenttj.ui.adapters.UserAdapterCashing
import kotlinx.coroutines.*

class HomeFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()

    // binding
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw RuntimeException("Error with FragmentHomeBinding")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData.observe(viewLifecycleOwner) { list ->
            binding.recyclerView.adapter = UserAdapter {
                val bundle = Bundle()
                bundle.putInt(UserFragment.INTENT_EXTRA, it)
                (activity
                    ?.supportFragmentManager
                    ?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment)
                    .navController
                    .navigate(R.id.action_fragmentHome_to_userFragment2, bundle)
            }.also {
                binding.skeletonView.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                CoroutineScope(Dispatchers.IO).launch {
                    it.submitData(list)
                }
            }
        }

        // кэширования без пагинации
        viewModel.getLiveDataCash.observe(viewLifecycleOwner) {list ->
            binding.recyclerView.adapter = UserAdapterCashing(list) {
                val bundle = Bundle()
                bundle.putInt(UserFragment.INTENT_EXTRA, it)
                (activity
                    ?.supportFragmentManager
                    ?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment)
                    .navController
                    .navigate(R.id.action_fragmentHome_to_userFragment2, bundle)
            }.also {
                binding.skeletonView.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
            }
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, HORIZONTAL))
            addItemDecoration(DividerItemDecoration(context, VERTICAL))
            hasFixedSize()
        }

        // кэширования без пагинации
        viewModel.getAllUserFromRetrofit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}