package io.embry.monsieurrobot.data

/**
 * This is just a pojo for the
 * robot.
 *
 * @author harshoverseer
 */
data class Robot constructor(var direction: String,
                             var x : Int,
                             var y: Int) {


    fun getReport() : String {
        return String.format("%d,%d,%s", x,y,direction)
    }
}