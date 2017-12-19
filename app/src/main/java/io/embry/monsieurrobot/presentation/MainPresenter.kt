package io.embry.monsieurrobot.presentation

import io.embry.monsieurrobot.data.Robot
import io.embry.monsieurrobot.domain.Usecases
import io.embry.monsieurrobot.domain.Usecases.Companion.validateDirectionText

/**
 * Presentation logic for the one
 * and only activity in this app.
 *
 * @author harshoverseer
 */
class MainPresenter {

    lateinit private var view: ViewSurface

    lateinit var usecases: Usecases

    private var hasRobotBeenPlaced: Boolean = false

    //region lifecycle

    fun onStart(view: ViewSurface) {
        this.view = view
        usecases = Usecases(Robot(Usecases.north, 0, 0))
    }

    fun onStop() {
        //dispose off any signals here.
    }

    //endregion


    //region ui interactions
    fun handleSubmitClicked(command: String) {
        evaluateCommand(command)
    }

    fun enableInput(enable: Boolean) {
        view.enableSubmitButton(enable)
    }


    //endregion

    //region private

    private fun evaluateCommand(command: String) {
        if (!hasRobotBeenPlaced) {
            if (!command.startsWith(Usecases.placeCommand, true)) {
                view.showPlacementError()
                return
            } else {
                runPlaceCommand(command)
            }
        } else {
            if (sanitiseInput(command)) {
                view.showReport(usecases.getRobotPositionReport())
            }
        }
    }

    private fun sanitiseInput(command: String): Boolean {
        if (command.startsWith(Usecases.placeCommand, true)) {
            return runPlaceCommand(command)
        }
        if (command.startsWith(Usecases.rotateLeft, true)) {
            usecases.rotateRobotLeft()
            return true
        }

        if (command.startsWith(Usecases.rotateRight, true)) {
            usecases.rotateRobotRight()
            return true
        }

        if (command.startsWith(Usecases.moveCommand, true)) {
            if (!usecases.moveRobot()) {
                view.showOutOfBoundsError()
                return false
            } else {
                return true
            }
        }

        if (command.startsWith(Usecases.report, true)) {
            view.showReport(usecases.getRobotPositionReport())
            return true
        }
        view.showInvalidCommandError()
        return false
    }

    private fun runPlaceCommand(command: String): Boolean {
        var sanitisedCommand = command.replace(" ", "")
        sanitisedCommand = sanitisedCommand.replace(Usecases.placeCommand.toLowerCase(), "")
        val placementList = sanitisedCommand.split(",")
        if (placementList.size == 3) {
            val direction = placementList[2]

            if (validateDirectionText(direction)) {
                try {
                    val x = placementList[0].toInt()
                    val y = placementList[1].toInt()
                    if (!usecases.placeRobot(direction, x, y)) {
                        view.showOutOfBoundsError()
                        return false
                    } else {
                        if (!hasRobotBeenPlaced) {
                            hasRobotBeenPlaced = true
                        }
                        view.showReport(usecases.getRobotPositionReport())
                        return true
                    }
                } catch (exception: NumberFormatException) {
                    return false
                }

            } else {
                return false
            }

        } else {
            return false
        }
    }

    //endregion


    interface ViewSurface {
        fun showPlacementError()
        fun showInvalidCommandError()
        fun showReport(report: String)
        fun enableSubmitButton(enable: Boolean)
        fun showOutOfBoundsError()
    }
}
