package hu.kiti.development.nail_timer

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import hu.kiti.development.nail_timer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.createProgramButton.setOnClickListener { onCreateProgramButtonClicked() }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.menu_settings) {
            return true
        }
        if (id == R.id.menu_about) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    internal fun onCreateProgramButtonClicked() {
        val intent = Intent(this, ProgramActivity::class.java)
        startActivity(intent)
    }
}