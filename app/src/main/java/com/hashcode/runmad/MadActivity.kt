package com.hashcode.runmad

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_mad.*
import java.util.*

class MadActivity : AppCompatActivity() {

    lateinit var firebaseAnalytics: FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mad)
        firebaseAnalytics = FirebaseAnalytics.getInstance(applicationContext)
        buttonRunMad.setOnClickListener { v ->
            showProgressDialog(this, "Run Mad", "Dear student, I'm working on it...")
            val handler = Handler()
            handler.postDelayed( Runnable { kotlin.run {
                stopProgressDialog()
                val arr: Array<String> = arrayOf("You will run mad during your next paper",
                        "Sleep now and you will wake up being mad",
                        "You will run mad during revision",
                        "You will run mad before you last paper",
                        "You will run mad while eating",
                        "You will run mad in the bathroom",
                        "You will run mad in the toilet")
                val num = Random().nextInt(arr.size - 1) + 0
                val msg = arr[num]
                Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
                logClickEvent(msg)
            } }, 2000)
            }
    }

    fun logClickEvent(how: String){
        val bundle = Bundle()
        bundle.putString("run_mad_button_click", "clicked")
        bundle.putString("run_made_medium", how)
        bundle.putLong("click_time", System.currentTimeMillis())
        firebaseAnalytics.logEvent("button_click", bundle)
    }

    var progressDialog: ProgressDialog? = null
    fun showProgressDialog(context: Context, title: String, message: String) {
        progressDialog = ProgressDialog(context)
        progressDialog!!.setTitle(title)
        progressDialog!!.setMessage(message)
        progressDialog!!.progress = 40
        progressDialog!!.progress = ProgressDialog.STYLE_SPINNER
        progressDialog!!.isIndeterminate = true
        progressDialog!!.show()
    }

    fun stopProgressDialog() {
        if (progressDialog != null) progressDialog!!.dismiss()
    }

}
