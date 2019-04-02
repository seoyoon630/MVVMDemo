package com.exmp.mvvm;

import com.exmp.mvvm.util.PP;

import java.util.concurrent.atomic.AtomicInteger;

public class NoteID {
    private final static AtomicInteger c = new AtomicInteger(PP.LAST_SEQNO.getInt(0));

    public static int getID() {
        return c.incrementAndGet();
    }
}
