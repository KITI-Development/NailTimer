package hu.kiti.development.nail_timer

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.OnClick

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
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

    @OnClick(R.id.create_program_button)
   internal fun onCreateProgramButtonClicked() {
        val intent = Intent(this, ProgramActivity::class.java)
        startActivity(intent)
    }
}