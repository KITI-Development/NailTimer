package hu.kiti.development.nail_timer.db.dao

import androidx.room.Dao
import androidx.room.Query
import hu.kiti.development.nail_timer.models.Program

@Dao
interface ProgramDao {

    @Query("SELECT * FROM program WHERE program.id == :programId")
    fun getProgram(programId: Long): Program
}