package hu.kiti.development.nail_timer

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick

class ProgramActivity : AppCompatActivity() {

    @BindView(R.id.program_name_edit_text)
    lateinit var programNameEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_program)
        ButterKnife.bind(this)

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Create program"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        programNameEditText.setSelection(programNameEditText.text.toString().length)
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

    @OnClick(R.id.add_layer_button)
    internal fun onAddLayerButtonClicked() {
        LayerDialog().getInstance()?.show(supportFragmentManager, "layer")
    }
}