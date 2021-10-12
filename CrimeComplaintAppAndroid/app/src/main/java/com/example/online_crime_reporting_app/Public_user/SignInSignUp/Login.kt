package com.example.online_crime_reporting_app.Public_user.SignInSignUp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.online_crime_reporting_app.Public_user.MainActivity
import com.example.online_crime_reporting_app.Public_user.UserProfile
import com.example.online_crime_reporting_app.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class Login : AppCompatActivity() {
    lateinit var btnSignIn:Button
    lateinit var btnSignUp:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin_signup_layout)

        btnSignIn=findViewById(R.id.btn_signIn)
        btnSignUp=findViewById(R.id.btn_signUp)

        btnSignIn.setOnClickListener{
            val dialog = BottomSheetDialog(this,R.style.BottomSheetDialogTheme)
            val view=layoutInflater.inflate(R.layout.signin_bottomsheet_dialog,null)

            val imgClose = view.findViewById<ImageView>(R.id.btn_close)
            val username = view.findViewById<EditText>(R.id.editText_username)
            val password = view.findViewById<EditText>(R.id.editText_password)
            val submit = view.findViewById<Button>(R.id.btn_submit_signIn)

            fun notEmpty() : Boolean{
                return username.text.isNotEmpty() && password.text.isNotEmpty()
            }

            imgClose.setOnClickListener {
                dialog.dismiss()
            }

            submit.setOnClickListener() {
                if (!notEmpty()){
                    Toast.makeText(this,"Enter all data", Toast.LENGTH_SHORT).show()
                }
                else{
                    val intent = Intent(this@Login, MainActivity::class.java)
                    startActivity(intent)
                }

            }

            dialog.setCancelable(false)
            dialog.setContentView(view)
            dialog.show()
        }

        btnSignUp.setOnClickListener{
            val dialog = BottomSheetDialog(this,R.style.BottomSheetDialogTheme)
            val view=layoutInflater.inflate(R.layout.signup_bottomsheet_dialog,null)

            val imgClose = view.findViewById<ImageView>(R.id.btn_close)

            imgClose.setOnClickListener {
                dialog.dismiss()
            }
            dialog.setCancelable(false)
            dialog.setContentView(view)
            dialog.show()
        }
    }
}