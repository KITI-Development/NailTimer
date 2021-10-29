package hu.kiti.development.nail_timer.db.dao

import androidx.room.*
import hu.kiti.development.nail_timer.models.Layer

@Dao
interface LayerDao {

    @Insert
    fun insertLayer(vararg layer: Layer)

    @Update
    fun updateLayer(vararg layer: Layer)


    @Delete
    fun deleteLayer(layer: Layer)

    @Query("SELECT * FROM layer WHERE layer.programId == :programId AND layer.id == :layerId")
    fun getLayer(programId: Long, layerId: Long): Layer
    
}