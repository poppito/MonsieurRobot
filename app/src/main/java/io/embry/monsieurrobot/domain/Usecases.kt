package io.embry.monsieurrobot.domain

import io.embry.monsieurrobot.data.Robot

/**
 * use cases for navigation of
 * monsieur robot of course.
 *
 * @author harshoverseer
 */
class Usecases constructor(val robot: Robot) {

    companion object {
        val placeCommand = "PLACE"
        val moveCommand = "MOVE"
        val south = "SOUTH"
        val north = "NORTH"
        val east = "EAST"
        val west = "WEST"
        val rotateLeft = "LEFT"
        val rotateRight = "RIGHT"
        val report = "REPORT"
    }

    fun provideRobot(): Robot {
        return robot
    }

    fun getRobotPositionReport(): String {
        return robot.getReport()
    }

    fun rotateRobotLeft() {
        if (robot.direction.equals(east)) {
            robot.direction = south
        }
        if (robot.direction.equals(west)) {
            robot.direction = north
        }
        if (robot.direction.equals(north)) {
            robot.direction = east
        }
        if (robot.direction.equals(south)) {
            robot.direction = west
        }
    }

    fun rotateRobotRight() {
        if (robot.direction.equals(east.toLowerCase())) {
            robot.direction = north
            return
        }
        if (robot.direction.equals(west.toLowerCase())) {
            robot.direction = south
            return
        }
        if (robot.direction.equals(north.toLowerCase())) {
            robot.direction = west
            return
        }
        if (robot.direction.equals(south.toLowerCase())) {
            robot.direction = east
            return
        }
    }

    fun placeRobot(direction: String, x: Int, y: Int): Boolean {
        if (x <= 4 && y <= 4 && x >= 0 && y >= 0) {
            robot.direction = direction
            robot.x = x
            robot.y = y
            return true
        } else {
            return false
        }
    }

    fun moveRobot(): Boolean {
        if (robot.direction.toLowerCase() == east.toLowerCase() && robot.x >= 0 && robot.x <= 3) {
            robot.x++
            return true
        }
        if (robot.direction.toLowerCase() == west.toLowerCase() && robot.x >= 1 && robot.x <= 4) {
            robot.x--
            return true
        }
        if (robot.direction.toLowerCase() == north.toLowerCase() && robot.y >= 0 && robot.y <= 3) {
            robot.y++
            return true
        }
        if (robot.direction.toLowerCase() == south.toLowerCase() && robot.y >= 1 && robot.y <= 4) {
            robot.y--
            return true
        }
        return false
    }
}