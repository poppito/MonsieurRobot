package io.embry.monsieurrobot

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import io.embry.monsieurrobot.domain.Usecases
import io.embry.monsieurrobot.presentation.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
        MainPresenter.ViewSurface,
        TextWatcher {

    lateinit var presenter: MainPresenter
    private var commandEntered: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txt_commandbox.addTextChangedListener(this)
        presenter = MainPresenter()
        presenter.onStart(this)
        btn_submit.setOnClickListener { presenter.handleSubmitClicked(commandEntered) }
    }


    //region viewsurface

    override fun showPlacementError() {
        txt_report.text = resources.getString(R.string.txt_error_placement)
        txt_report.setTextColor(resources.getColor(android.R.color.holo_red_dark))
    }

    override fun showReport(report: String) {
        txt_report.text = report
        txt_report.setTextColor(resources.getColor(android.R.color.black))
    }

    override fun enableSubmitButton(enable: Boolean) {
        btn_submit.isEnabled = enable
    }

    override fun showInvalidCommandError() {
        txt_report.text = resources.getString(R.string.txt_invalid_command)
    }

    override fun showOutOfBoundsError() {
        txt_report.text = resources.getString(R.string.txt_error_out_of_bounds)
        txt_report.setTextColor(resources.getColor(android.R.color.holo_red_dark))
    }

    //endregion


    //region textwatcher

    override fun afterTextChanged(s: Editable?) {
        if (s == null) {
            return
        }
        if (s.startsWith(Usecases.placeCommand, true) ||
                s.startsWith(Usecases.moveCommand, true) ||
                s.startsWith(Usecases.rotateRight, true) ||
                s.startsWith(Usecases.rotateLeft, true) ||
                s.startsWith(Usecases.report, true)) {
            presenter.enableInput(true)
            commandEntered = s.toString()
        } else {
            presenter.enableInput(false)
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    //endregion
}
