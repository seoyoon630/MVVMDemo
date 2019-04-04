package com.exmp.mvvm.util

enum class EE {
    SHOW_LIST
    ,MOVE_TO_ADD_NOTE;

    private var mObj: Any? = null

    fun msg(obj: Any): EE {
        mObj = obj
        return this
    }

    override fun toString(): String {
        return "$name,$mObj"
    }

    fun msg(): Any? {
        return mObj
    }
}