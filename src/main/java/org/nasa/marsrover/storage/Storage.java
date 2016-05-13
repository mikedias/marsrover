package org.nasa.marsrover.storage;

import org.nasa.marsrover.Field;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Mike Dias
 */
public class Storage {

    // ephemeral storage
    private static final ConcurrentMap<Integer, Field> MEM_STORAGE = new ConcurrentHashMap<>();

    public static ConcurrentMap<Integer, Field> getFieldStorage() {
        return MEM_STORAGE;
    }
}
