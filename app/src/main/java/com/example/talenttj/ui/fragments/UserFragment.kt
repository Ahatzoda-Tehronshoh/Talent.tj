package com.example.talenttj.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.talenttj.viewmodel.MainViewModel
import com.example.talenttj.databinding.FragmentUserBinding
import com.example.talenttj.ui.adapters.SkillAdapter
import com.example.talenttj.viewmodel.UserViewModel


class UserFragment() : Fragment() {

    companion object {
        const val INTENT_EXTRA = "id"
    }

    private val viewModel: UserViewModel by viewModels()

    // binding
    private var _binding: FragmentUserBinding? = null
    private val binding: FragmentUserBinding
        get() = _binding ?: throw RuntimeException("user_fragment_layout is null!!!")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userid = requireArguments().getInt(INTENT_EXTRA)

        val glide = Glide.with(this)

        binding.skillRecyclerView.apply {
            layoutManager = GridLayoutManager(view.context, 3)
            hasFixedSize()
        }

        viewModel.getUserWithId.observe(viewLifecycleOwner) {
            val loadImage = glide.load(it.Image)
            val name = it.name + " " + it.surname
            loadImage.into(binding.userImage)
            binding.apply {
                fio.text = "${name}" ?: ""
                professionTextView.text = viewModel.professionToString
                educationTextView.text = it.education
                locationTextView.text = "${it.city} ${it.country}"
                skillRecyclerView.adapter = SkillAdapter(viewModel.listOfSkill)

                contactButton.setOnClickListener { _ ->
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:${it.phone}")
                    startActivity(Intent.createChooser(intent, "Choose application:"))
                }
            }
        }

        viewModel.getUserWithID(userid)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}