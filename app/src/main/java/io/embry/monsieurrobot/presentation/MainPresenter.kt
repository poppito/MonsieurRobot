package io.embry.monsieurrobot.presentation

import io.embry.monsieurrobot.data.Robot
import io.embry.monsieurrobot.domain.Usecases

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
            }
        } else {
            if (sanitiseInput(command)) {
                usecases.getRobotPositionReport()
            } else {
                view.showInvalidCommandError()
            }
        }
    }

    private fun sanitiseInput(command: String): Boolean {
        if (command.startsWith(Usecases.placeCommand)) {
            hasRobotBeenPlaced = true
            return true
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
            usecases.moveRobot()
            return true
        }

        if (command.startsWith(Usecases.report, true)) {
            view.showReport(usecases.getRobotPositionReport())
            return true
        }

        return false
    }

    //endregion


    interface ViewSurface {
        fun showPlacementError()
        fun showInvalidCommandError()
        fun showReport(report: String)
        fun enableSubmitButton(enable: Boolean)
    }
}
