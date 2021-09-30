package ar.com.example.notbored.ui.suggestFragment

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import ar.com.example.notbored.R
import ar.com.example.notbored.databinding.FragmentSuggestBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import ar.com.example.notbored.data.Category
import ar.com.example.notbored.data.RecreationalActivity
import ar.com.example.notbored.domain.repository.DataRepository


class SuggestFragment : Fragment(R.layout.fragment_suggest) {

    private lateinit var binding:FragmentSuggestBinding
    private val args: SuggestFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSuggestBinding.bind(view)
        setValues(args.suggestion)
        buttonFunction()
    }

    private fun buttonFunction() {
        whereIam()

        if (args.randomWasPressed){
            binding.btnTryAnother.setOnClickListener {
                val data = DataRepository.listOfCategories[(0..10).random()].activities[(0..2).random()]
                setValues(data)
            }
        }else{
            binding.btnTryAnother.setOnClickListener {
                val anotherSuggestion = whereIam().activities[(0..2).random()]
                setValues(anotherSuggestion)
            }

        }
    }

    private fun whereIam(): Category {
        var theList = DataRepository.listOfCategories[0]
        val actualCategory = args.suggestion.categoryName
         for (myObject in DataRepository.listOfCategories){
            if (actualCategory == myObject.categoryName){
                return myObject
            }
             theList = myObject
        }
        return theList
    }

    private fun setValues(suggestion:RecreationalActivity) {
        binding.tvSuggestionName.text = suggestion.activityName
        binding.tvNumberOfParticipants.text = args.participants
        binding.tvPrice.text = calculatePrice(suggestion.price)
        showCategoryView(suggestion.categoryName)
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = args.appBarTitle
    }

    private fun showCategoryView(categoryName:String) {
        if (args.randomWasPressed){
            binding.categoryView.visibility = View.VISIBLE
            binding.tvCategoryName.text = categoryName
        }else binding.categoryView.visibility = View.GONE
    }

    private fun calculatePrice(price:Double): String {
        return when(price){
            0.0 -> "Free"
            in 0.1..0.3 -> "Low"
            in 0.4..0.6 -> "Medium"
            else -> "High"
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        // handle the up button here
        return NavigationUI.onNavDestinationSelected(item!!,
            requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

}