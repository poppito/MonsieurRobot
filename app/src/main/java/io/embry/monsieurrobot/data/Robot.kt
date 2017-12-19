package io.embry.monsieurrobot.data

/**
 * This is just a pojo for the
 * robot.
 *
 * @author harshoverseer
 */
data class Robot constructor(var direction: Direction,
                             var x : Int,
                             var y: Int) {

    enum class Direction {
        NORTH,
        EAST,
        WEST,
        SOUTH
    }
}