package com.mmall.concurrency.example.lock;

import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
@ThreadSafe
public class LockExample1 {
    private final Map<String, Data> map = new TreeMap<>();

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

       private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    public Data get (String key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys() {
        readLock.lock();
        try {
            return map.keySet();
        }  finally {
            readLock.unlock();
        }
    }
    public Data put(String kye,Data value) {
        writeLock.lock();
        try {
            return map.put(kye, value);
        } finally {
            writeLock.unlock();
        }
    }


    class Data {

    }


}
