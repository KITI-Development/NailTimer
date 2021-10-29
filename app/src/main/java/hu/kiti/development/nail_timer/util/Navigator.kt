package hu.kiti.development.nail_timer.util

import android.content.Context
import android.content.Intent
import hu.kiti.development.nail_timer.ui.ProgramActivity

object Navigator {

    fun openProgram(context: Context) {
       openProgram(context, -1L)
    }

    fun openProgram(context: Context, programId: Long) {
        val intent = Intent(context, ProgramActivity::class.java)
        intent.putExtra(Constants.KEY_PROGRAM_ID, programId)
        context.startActivity(intent)
    }
}