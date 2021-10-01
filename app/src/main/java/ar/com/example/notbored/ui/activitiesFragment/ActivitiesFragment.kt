package ar.com.example.notbored.ui.activitiesFragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ar.com.example.notbored.data.Category
import ar.com.example.notbored.databinding.FragmentActivitiesBinding
import ar.com.example.notbored.domain.repository.DataRepository
import ar.com.example.notbored.presentation.SharedViewModel

class ActivitiesFragment : Fragment(ar.com.example.notbored.R.layout.fragment_activities), CategoryAdapter.OnClick {


    private lateinit var binding: FragmentActivitiesBinding
    private val args :ActivitiesFragmentArgs by navArgs()
    private val categories = DataRepository.listOfCategories
    private val viewModel : SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentActivitiesBinding.bind(view)
        setActionBar()
        initRecyclerView()
        setTitle()

    }

    private fun setTitle() {
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Activities"
    }

    private fun initRecyclerView() {
        binding.rvCategories.adapter = CategoryAdapter(categories, this@ActivitiesFragment)
    }

    private fun setActionBar() {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
    }

    override fun onCategoryClick(category: Category) {
        viewModel.saveCategorySelected(category)
        val action = ActivitiesFragmentDirections.actionActivitiesFragmentToSuggestFragment(participants = args.participants)
        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(ar.com.example.notbored.R.menu.random, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            ar.com.example.notbored.R.id.btn_random -> {
                val action = ActivitiesFragmentDirections.actionActivitiesFragmentToSuggestFragment(
                    args.participants, "Random", true)
                findNavController().navigate(action)
                true
            }else -> super.onOptionsItemSelected(item)
        }
    }




}