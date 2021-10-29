package hu.kiti.development.nail_timer.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import hu.kiti.development.nail_timer.models.Layer
import hu.kiti.development.nail_timer.models.Program

@Dao
interface ProgramDao {

    @Query("SELECT * FROM program WHERE program.id == :programId")
    fun getProgram(programId: Long): Program

    @Insert
    fun insertProgram(vararg program: Program)

    @Update
    fun updateProgram(vararg program: Program)
}