package ar.com.example.notbored.ui.homeFragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import ar.com.example.notbored.databinding.FragmentHomeBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import ar.com.example.notbored.R
import ar.com.example.notbored.presentation.SharedViewModel


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding:FragmentHomeBinding
    private val viewModel : SharedViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        watchText()
        toTermsAndConditions()
        noActionBar()
        toActivitiesFragment()
    }

    private fun noActionBar() {
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    private fun toActivitiesFragment() {
        binding.btnStart.setOnClickListener {
            closeSoftKeyboard(requireContext(), requireView())
            val etNumberValue = binding.etNumber.text.toString()
            viewModel.checkEditText(etNumberValue)
            viewModel.fetchEditTextCheck.observe(viewLifecycleOwner, Observer {
                when(it){
                    false -> {
                        findNavController().navigate(R.id.action_homeFragment_to_activitiesFragment)
                    }
                    true -> {
                        val action = HomeFragmentDirections.actionHomeFragmentToActivitiesFragment(etNumberValue)
                        findNavController().navigate(action)}
                }
            })
        }
    }


    private fun toTermsAndConditions() {
        binding.termsLink.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_termsAndConditionsFragment)
        }
    }

    private fun watchText() {
        binding.etNumber.doAfterTextChanged {
            val etValue = binding.etNumber.text.toString()
            viewModel.preventEditTextBeginWithZero(etValue).observe(viewLifecycleOwner, Observer { itsZero ->
                when(itsZero){
                    true -> binding.btnStart.isEnabled = false
                    false -> binding.btnStart.isEnabled = true
                }
            })
        }
    }
    private fun closeSoftKeyboard(context: Context, view: View) {
        val inputMethod = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethod.hideSoftInputFromWindow(view.windowToken, 0)
        view.clearFocus()
    }
}