package hu.kiti.development.nail_timer.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import hu.kiti.development.nail_timer.R
import hu.kiti.development.nail_timer.databinding.ActivityMainBinding
import hu.kiti.development.nail_timer.db.AppDatabase
import hu.kiti.development.nail_timer.ui.ProgramActivity
import hu.kiti.development.nail_timer.util.Navigator
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val scope = CoroutineScope(Job() + Dispatchers.Default)

    private lateinit var adapter: ProgramAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ProgramAdapter(listOf())

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.createProgramButton.setOnClickListener { onCreateProgramButtonClicked() }
    }

    @ExperimentalCoroutinesApi
    override fun onResume() {
        super.onResume()
        listPrograms()
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

    private fun onCreateProgramButtonClicked() {
        Navigator.openProgram(this)
    }

    private fun listPrograms() {
        scope.async {
            val programs = AppDatabase.getInstance(this@MainActivity).programDao().getPrograms()
            withContext(Dispatchers.Main) {
                adapter.update(programs)
            }
        }
    }
}