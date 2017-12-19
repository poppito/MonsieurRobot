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

    fun getRobotPositionReport(): String {
        return robot.getReport()
    }

    fun rotateRobotLeft() {
        if (robot.direction.equals(east, true)) {
            robot.direction = south
            return
        }
        if (robot.direction.equals(west, true)) {
            robot.direction = north
            return
        }
        if (robot.direction.equals(north, true)) {
            robot.direction = east
            return
        }
        if (robot.direction.equals(south, true)) {
            robot.direction = west
            return
        }
    }

    fun rotateRobotRight() {
        if (robot.direction.equals(east, true)) {
            robot.direction = north
            return
        }
        if (robot.direction.equals(west, true)) {
            robot.direction = south
            return
        }
        if (robot.direction.equals(north, true)) {
            robot.direction = west
            return
        }
        if (robot.direction.equals(south, true)) {
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
        if ((robot.direction.equals(east, true)) && robot.x >= 0 && robot.x <= 3) {
            robot.x++
            return true
        }
        if (robot.direction.equals(west, true) && robot.x >= 1 && robot.x <= 4) {
            robot.x--
            return true
        }
        if (robot.direction.equals(north, true) && robot.y >= 0 && robot.y <= 3) {
            robot.y++
            return true
        }
        if (robot.direction.equals(south, true) && robot.y >= 1 && robot.y <= 4) {
            robot.y--
            return true
        }
        return false
    }
}