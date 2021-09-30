package ar.com.example.notbored.ui.activitiesFragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ar.com.example.notbored.data.Category
import ar.com.example.notbored.databinding.FragmentActivitiesBinding
import ar.com.example.notbored.repository.DataRepository




class ActivitiesFragment : Fragment(ar.com.example.notbored.R.layout.fragment_activities), CategoryAdapter.OnClick {


    private lateinit var binding: FragmentActivitiesBinding
    private val participants :ActivitiesFragmentArgs by navArgs()
    private val categories = DataRepository.listOfCategories

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentActivitiesBinding.bind(view)
        setActionBar()
        setDataAndRecyclerView()
        setTitle()

    }

    private fun setTitle() {
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Activities"
    }

    private fun setDataAndRecyclerView() {

        binding.rvCategories.adapter = CategoryAdapter(categories, this@ActivitiesFragment)
    }

    private fun setActionBar() {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
    }

    override fun onCategoryClick(category: Category) {
        val suggestionList = category.activities[(0..2).random()]
        val action = ActivitiesFragmentDirections.actionActivitiesFragmentToSuggestFragment(participants = participants.participants,
            suggestionList)
        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(ar.com.example.notbored.R.menu.random, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val randomSuggestionList = categories[(0..10).random()].activities[(0..2).random()]
        return when (item.itemId) {
            ar.com.example.notbored.R.id.btn_random -> {
                val action = ActivitiesFragmentDirections.actionActivitiesFragmentToSuggestFragment(participants = participants.participants,
                    randomSuggestionList, "Random", true)
                findNavController().navigate(action)
                true
            }else -> super.onOptionsItemSelected(item)
        }
    }




}