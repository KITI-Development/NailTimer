package hu.kiti.development.nail_timer.models

import androidx.annotation.Keep

@Keep
class Program {

    var id: Long = 0
    var name: String = ""
    var layers: List<Layer> = emptyList();

}