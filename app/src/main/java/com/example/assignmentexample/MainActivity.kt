package com.example.assignmentexample

import android.app.AlertDialog
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (resources.getBoolean(R.bool.portrait_only)){
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        btnSubmit.setOnClickListener {
            when{
                etEmail.text.isNullOrEmpty()->{
                    etEmail.error = getString(R.string.plesae_enter_the_email)
                    etEmail.requestFocus()
                }
                !etEmail.text.isValidEmail()->{
                    etEmail.error = getString(R.string.plesae_enter_the_valid_email)
                    etEmail.requestFocus()
                }
                etPassword.text.isNullOrEmpty()->{
                    etPassword.error = getString(R.string.Please_enter_the_password)
                    etPassword.requestFocus()
                }
                etConfirmPassword.text.isNullOrEmpty()->{
                    etConfirmPassword.error =getString(R.string.Please_enter_the_confirm_password)
                    etConfirmPassword.requestFocus()
                }
                else ->{
                    if (etPassword.text.toString() == etConfirmPassword.text.toString()){
                        val alertDialog: AlertDialog = AlertDialog.Builder(this@MainActivity).create()
                        alertDialog.setTitle("Login Status")
                        alertDialog.setMessage("Login Successful with ${etEmail.text.toString()}")
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK"
                        ) { dialog, _ -> dialog.dismiss() }
                        alertDialog.show()
                    }else{
                        etConfirmPassword.error =getString(R.string.password_not_match)
                        etConfirmPassword.requestFocus()
                    }
                }
            }

        }
    }
    fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}