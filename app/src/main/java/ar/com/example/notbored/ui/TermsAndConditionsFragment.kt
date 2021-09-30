package ar.com.example.notbored.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import ar.com.example.notbored.R
import ar.com.example.notbored.databinding.FragmentTermsAndConditionsBinding

class TermsAndConditionsFragment : Fragment(R.layout.fragment_terms_and_conditions) {

    private lateinit var binding:FragmentTermsAndConditionsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTermsAndConditionsBinding.bind(view)
        back()
    }

    private fun back() {
        binding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}