package hu.kiti.development.nail_timer.models

import androidx.annotation.Keep

@Keep
class Layer() {

    var order: Int = 0
    var name: String = ""
    var type: LayerType = LayerType.COLOR
    var durationInSec: Int = 0
}