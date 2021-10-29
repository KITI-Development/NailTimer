package hu.kiti.development.nail_timer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import hu.kiti.development.nail_timer.databinding.DialogLayerBinding
import hu.kiti.development.nail_timer.models.Layer

class LayerDialog : DialogFragment() {

    private var _binding: DialogLayerBinding? = null
    private val binding get() = _binding!!

    fun getInstance(layer: Layer): LayerDialog {
        return LayerDialog()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogLayerBinding.inflate(inflater, container, false)

        val list = ArrayList<String>()
        list.add("10 sec")
        list.add("20 sec")
        list.add("30 sec")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list)
        binding.durationSpinner.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
