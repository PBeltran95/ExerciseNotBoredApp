package ar.com.example.notbored.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import ar.com.example.notbored.databinding.FragmentHomeBinding
import androidx.appcompat.app.AppCompatActivity
import ar.com.example.notbored.R


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding:FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        watchText()
        toTermsAndConditions()
        noActionBar()
        toActivities()
    }

    private fun toActivities() {
        binding.btnStart.setOnClickListener {
            if (!binding.etNumber.text.isNullOrEmpty()){
                val participants = binding.etNumber.text.toString()
                val action = HomeFragmentDirections.actionHomeFragmentToActivitiesFragment(participants)
                findNavController().navigate(action)
            }else{
                findNavController().navigate(R.id.action_homeFragment_to_activitiesFragment)
            }
        }
    }

    private fun noActionBar() {
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    private fun toTermsAndConditions() {
        binding.termsLink.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_termsAndConditionsFragment)
        }
    }

    private fun watchText() {
        binding.etNumber.addTextChangedListener(object : TextWatcher
        { override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.btnStart.isEnabled = binding.etNumber.text?.startsWith("0") != true
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }
}