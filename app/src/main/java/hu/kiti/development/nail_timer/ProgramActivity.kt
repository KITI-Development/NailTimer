package hu.kiti.development.nail_timer

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import hu.kiti.development.nail_timer.databinding.ActivityProgramBinding
import hu.kiti.development.nail_timer.db.AppDatabase
import hu.kiti.development.nail_timer.models.Layer
import hu.kiti.development.nail_timer.models.Program
import hu.kiti.development.nail_timer.util.CommonUtil

class ProgramActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProgramBinding

    private lateinit var program: Program

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

        binding.addLayerButton.setOnClickListener { onAddLayerButtonClicked() }

        val programId = intent.getLongExtra(KEY_PROGRAM_ID, -1L);
        if (programId == -1L) {
            program = Program(CommonUtil.generateId())
        } else {
            program = AppDatabase.getInstance(this).programDao().getProgram(programId)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.program, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.menu_save) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    internal fun onAddLayerButtonClicked() {
        var layer = Layer(CommonUtil.generateId())
        LayerDialog().getInstance(layer).show(supportFragmentManager, "layer")
    }

    companion object {
        public const val KEY_PROGRAM_ID = "program_id"
    }
}