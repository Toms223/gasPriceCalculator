package com.example.gaspricecalculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import com.example.gaspricecalculator.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val carConsumption = binding.CarConsumption
        val gasPrice = binding.GasPrice
        val distance = binding.distance
        var lastPressed = carConsumption

        binding.calculate.setOnClickListener {
            distance.onEditorAction(EditorInfo.IME_ACTION_DONE)
            gasPrice.onEditorAction(EditorInfo.IME_ACTION_DONE)
            carConsumption.onEditorAction(EditorInfo.IME_ACTION_DONE)
            val consumption = carConsumption.text.toString().toFloat()
            val price = gasPrice.text.toString().removeSuffix("€").toFloat()
            val km = distance.text.toString().lowercase().removeSuffix("km").toFloat()
            val liters = (consumption * km)/100
            val total = (price * liters).toString() + "€"
            binding.totalPrint.text = total
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}