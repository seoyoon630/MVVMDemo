package com.exmp.mvvm;

import java.util.concurrent.atomic.AtomicInteger;

public class NoteID {
    private final static AtomicInteger c = new AtomicInteger(0);

    public static int getID() {
        return c.incrementAndGet();
    }
}
