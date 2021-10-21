package hu.kiti.development.nail_timer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import hu.kiti.development.nail_timer.databinding.DialogLayerBinding

class LayerDialog : DialogFragment() {

    private var _binding: DialogLayerBinding? = null
    private val binding get() = _binding!!

    fun getInstance(): LayerDialog {
        return LayerDialog()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogLayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
