package hu.kiti.development.nail_timer.models

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity
class Program(
    @PrimaryKey
    val id: Long
) {

    var name: String = ""
}