package hu.kiti.development.nail_timer.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hu.kiti.development.nail_timer.R
import hu.kiti.development.nail_timer.databinding.ActivityProgramBinding
import hu.kiti.development.nail_timer.db.AppDatabase
import hu.kiti.development.nail_timer.models.Layer
import hu.kiti.development.nail_timer.models.Program
import hu.kiti.development.nail_timer.util.CommonUtil
import hu.kiti.development.nail_timer.util.Constants
import kotlinx.coroutines.*

class ProgramActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProgramBinding

    private lateinit var program: Program

    private var isNew: Boolean = false

    private val scope = CoroutineScope(Job() + Dispatchers.Default)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgramBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Create program"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        binding.programNameEditText.setSelection(binding.programNameEditText.text.toString().length)
        binding.programNameEditText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(editable: Editable) {
                program.name = editable.toString()
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

        binding.addLayerButton.setOnClickListener { onAddLayerButtonClicked() }

        val programId = intent.getLongExtra(Constants.KEY_PROGRAM_ID, -1L);
        if (programId == -1L) {
            isNew = true
            program = Program(CommonUtil.generateId())
            binding.programNameEditText.setText("My Program")
        } else {
            scope.async {
                program =
                    AppDatabase.getInstance(this@ProgramActivity).programDao().getProgram(programId)
                withContext(Dispatchers.Main) {
                    binding.programNameEditText.setText(program.name)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.program, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.menu_save) {
            saveProgram()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun onAddLayerButtonClicked() {
        LayerDialog().getInstance(program.id, -1L).show(supportFragmentManager, "layer")
    }

    private fun saveProgram() {
        scope.async {
            if (isNew) {
                AppDatabase.getInstance(this@ProgramActivity).programDao().insertProgram(program)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ProgramActivity, "Program is saved.", Toast.LENGTH_SHORT)
                        .show()
                    finish()
                }
            } else {
                AppDatabase.getInstance(this@ProgramActivity).programDao().updateProgram(program)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ProgramActivity, "Program is updated.", Toast.LENGTH_SHORT)
                        .show()
                    finish()
                }
            }
        }
    }
}