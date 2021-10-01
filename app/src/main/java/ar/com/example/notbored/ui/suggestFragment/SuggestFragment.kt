package ar.com.example.notbored.ui.suggestFragment

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import ar.com.example.notbored.R
import ar.com.example.notbored.databinding.FragmentSuggestBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import ar.com.example.notbored.data.Category
import ar.com.example.notbored.data.RecreationalActivity
import ar.com.example.notbored.domain.repository.DataRepository
import ar.com.example.notbored.presentation.SharedViewModel


class SuggestFragment : Fragment(R.layout.fragment_suggest) {

    private lateinit var binding: FragmentSuggestBinding
    private val args: SuggestFragmentArgs by navArgs()
    private val viewModel : SharedViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSuggestBinding.bind(view)
        onRandomPressed()
        buttonFunction()
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
    }

    private fun onRandomPressed() {
        if (!args.randomWasPressed) {
            viewModel.categorySelected.observe(viewLifecycleOwner, Observer {
                viewModel.getRandomFromCategory(it)
            })
            viewModel.randomFromCategory.observe(viewLifecycleOwner, Observer{
                setValues(it)
            })
        } else {
            viewModel.apply {
                onRandomPressed()
                totallyRandomSuggestion.observe(viewLifecycleOwner, Observer {
                    setValues(it)
                })
            }
        }
    }

    private fun buttonFunction() {
            binding.btnTryAnother.setOnClickListener {
                onRandomPressed()
            }
    }


    private fun setValues(suggestion: RecreationalActivity) {
        binding.tvSuggestionName.text = suggestion.activityName
        binding.tvNumberOfParticipants.text = args.participants
        drawPrice(suggestion.price)
        showCategoryView(suggestion.categoryName)
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = args.appBarTitle
    }

    private fun showCategoryView(categoryName: String) {
        if (args.randomWasPressed) {
            binding.categoryView.visibility = View.VISIBLE
            binding.tvCategoryName.text = categoryName
        } else binding.categoryView.visibility = View.GONE
    }

    private fun drawPrice(price: Double) {
        viewModel.priceToString(price)
        viewModel.priceValue.observe(viewLifecycleOwner, Observer {
            binding.tvPrice.text = it
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,
            requireView().findNavController()) || super.onOptionsItemSelected(item)
    }
}