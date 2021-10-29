package hu.kiti.development.nail_timer.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import hu.kiti.development.nail_timer.databinding.DialogLayerBinding
import hu.kiti.development.nail_timer.db.AppDatabase
import hu.kiti.development.nail_timer.models.Layer
import hu.kiti.development.nail_timer.util.CommonUtil
import hu.kiti.development.nail_timer.util.Constants
import kotlinx.coroutines.*

class LayerDialog : DialogFragment() {

    private var _binding: DialogLayerBinding? = null
    private val binding get() = _binding!!

    private lateinit var coat: Layer

    private val scope = CoroutineScope(Job() + Dispatchers.Default)

    private var isNew: Boolean = false

    fun getInstance(programId: Long, coatId: Long): LayerDialog {
        val dialog = LayerDialog()
        val bundle = Bundle()
        bundle.putLong(Constants.KEY_PROGRAM_ID, programId)
        bundle.putLong(Constants.KEY_COAT_ID, coatId)
        dialog.arguments = bundle
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogLayerBinding.inflate(inflater, container, false)

        binding.coatNameEditText.setSelection(binding.coatNameEditText.text.toString().length)
        binding.coatNameEditText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(editable: Editable) {
                coat.name = editable.toString()
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
            }
        })


        val programId = arguments?.getLong(Constants.KEY_PROGRAM_ID, -1L);
        val coatId = arguments?.getLong(Constants.KEY_COAT_ID, -1L);

        if (coatId == -1L) {
            isNew = true
            coat = Layer(CommonUtil.generateId())
            coat.programId = programId!!
            binding.coatNameEditText.setText("coat")
        } else {
            scope.async {
                coat =
                    AppDatabase.getInstance(requireContext()).layerDao()
                        .getLayer(programId!!, coatId!!)
                withContext(Dispatchers.Main) {
                    binding.coatNameEditText.setText(coat.name)
                }
            }
        }

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
