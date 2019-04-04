package com.exmp.mvvm.viewmodel

import com.exmp.mvvm.util.EE
import java.util.*

open class BaseObservable : Observable() {

    fun notify(e: EE) {
        setChanged()
        notifyObservers(e)
    }

}