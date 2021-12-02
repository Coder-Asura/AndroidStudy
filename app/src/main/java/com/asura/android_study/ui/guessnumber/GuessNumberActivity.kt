package com.asura.android_study.ui.guessnumber

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.asura.android_study.R
import com.asura.android_study.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_guess_number.*
import kotlinx.android.synthetic.main.activity_main.*

class GuessNumberActivity : BaseActivity() {

    var input: Int = -1
    var answer: Int = -1

    override fun setLayoutId(): Int {
        return R.layout.activity_guess_number
    }

    override fun initView() {
        btn_ask.setOnClickListener {
            try {
                input = input_ask.text.toString().toInt()
            } catch (e: Exception) {
                Toast.makeText(this, "请输入0-100的数字！！！", Toast.LENGTH_SHORT).show()
            }
            if (input < 0 || input > 100) {
                Toast.makeText(this, "请输入0-100的数字！！！", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Toast.makeText(this, "请答题人答题！", Toast.LENGTH_SHORT).show()
            input_ask.setText("")
            tv_result.text = ""
        }

        btn_answer.setOnClickListener {
            try {
                answer = input_answer.text.toString().toInt()
            } catch (e: Exception) {
                Toast.makeText(this, "请输入0-100的数字！！！", Toast.LENGTH_SHORT).show()
            }
            if (answer < 0 || answer > 100) {
                Toast.makeText(this, "请输入0-100的数字！！！", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (input == answer) {
                AlertDialog.Builder(this)
                    .setTitle("666")
                    .setMessage("你他娘的真是个天才！！！")
                    .setNegativeButton("再玩一次") { dialog, _ ->
                        input_ask.setText("")
                        input_answer.setText("")
                        tv_result.text = ""
                        dialog.dismiss()
                    }.create().show()

                tv_result.text = "你他娘的真是个天才！！！"
            } else if (input < answer) {
                tv_result.text = "再小一点！"
                Toast.makeText(this, "再小一点！", Toast.LENGTH_SHORT).show()
            } else {
                tv_result.text = "再大一点！"
                Toast.makeText(this, "再大一点！", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
