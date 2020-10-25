package hu.kiti.development.nail_timer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

class LayerDialog : DialogFragment() {

    fun getInstance(): LayerDialog? {
        return LayerDialog()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_layer, container)
    }

}
