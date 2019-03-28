package com.exmp.mvvm.util

import android.content.Context
import android.support.annotation.RawRes

object Util {
    fun raw2string(mContext : Context, @RawRes raw_resid: Int): String {
        return try {
            val res = mContext.resources
            val input = res.openRawResource(raw_resid)
            val b = ByteArray(input.available())
            input.read(b)
            input.close()
            String(b)
        } catch (e: Exception) {
            ""
        }
    }
}