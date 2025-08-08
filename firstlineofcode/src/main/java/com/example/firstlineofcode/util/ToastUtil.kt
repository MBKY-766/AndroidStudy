package com.example.firstlineofcode.util

import android.content.Context
import android.widget.Toast

class ToastUtil {

    //伴生对象中的方法和变量可以直接使用类名来访问，类似于 Java 中的 static 成员
    companion object {
        fun show(context: Context, msg: String?) {
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
        }
    }
}