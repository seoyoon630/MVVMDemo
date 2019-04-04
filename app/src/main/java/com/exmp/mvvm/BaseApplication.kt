package com.exmp.mvvm

import android.app.Application
import com.exmp.mvvm.util.FileUtil
import com.exmp.mvvm.util.PP

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        PP.CREATE(this)
        FileUtil.CREATE(this)
    }
}