package com.example.app

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app.entity.User
import com.example.app.widget.CodeView
import com.example.core.utils.CacheUtils
import com.example.core.utils.Utils
import com.example.lesson.LessonActivity

 class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val usernameKey = "username"
    private val passwordkey = "password"

    private lateinit var et_username: EditText
    private lateinit var et_password: EditText

    private var et_code: EditText? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        et_username=findViewById(R.id.et_username)
        et_password=findViewById(R.id.et_password)
        et_code=findViewById(R.id.et_code)
        et_username.setText(CacheUtils.get(usernameKey))
        et_password.setText(CacheUtils.get(passwordkey))
        val btn_login=findViewById<Button>(R.id.btn_login)
        val img_code=findViewById<CodeView>(R.id.code_view)
        Utils.toast("I am kotlin")
        btn_login.setOnClickListener(this)
        img_code.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        if(v is CodeView){
            v.updateCode()
        }else if(v is Button){
            login()
        }
    }

    private fun login(){
        val username=et_username.text.toString()
        val password=et_username.text.toString()
        val code=et_code!!.text.toString()

        var user=User(username,password,code)
        if(verify(user)){
            CacheUtils.save(usernameKey,username)
            CacheUtils.save(passwordkey,password)
            startActivity(Intent(this,LessonActivity::class.java))
        }

    }

    private fun verify(user:User):Boolean{
        if(user.username!=null&&user.username!!.length<4){
            Utils.toast("用户不合法")
            return false
        }
        if(user.password!=null&&user.password!!.length<4){
            Utils.toast("密码不合法")
            return false
        }

        return true
    }
}