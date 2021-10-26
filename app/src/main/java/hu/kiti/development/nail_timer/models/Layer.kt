package hu.kiti.development.nail_timer.models

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity
data class Layer(
    @PrimaryKey
    val id: Long
) {

    var programId: Long = 0
    var order: Int = 0
    var name: String = ""
    var type: LayerType = LayerType.COLOR
    var durationInSec: Int = 0

}