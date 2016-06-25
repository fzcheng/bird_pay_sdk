package com.cheyooh.tools.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public abstract class AbstractDistributedLock implements DistributedLock {
    //
    protected volatile boolean verbose;
    protected volatile Listener listener;
    protected final ReentrantLock lock = new ReentrantLock();

    //
    protected abstract void doLock();
    protected abstract void doUnlock();
    protected abstract boolean doTryLock();
    protected abstract void doLockInterruptibly() throws InterruptedException;
    protected abstract boolean doTryLock(long timeout, TimeUnit unit) throws InterruptedException;
    
    /**
     * 
     */
    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }
    
    public boolean isLocked() {
        return this.lock.isLocked();
    }
    
    public boolean isHeldByCurrentThread() {
        return this.lock.isHeldByCurrentThread();
    }
    
    /**
     * 
     */
    @Override
    public Listener getListener() {
        return this.listener;
    }

    @Override
    public void setListener(Listener listener) {
        this.listener = listener;
    }
    
    /**
     * 
     */
    @Override
    public void lock() {
        //
        this.lock.lock();
        if(this.lock.getHoldCount() > 1) return;
        
        //
        boolean succeed = false;
        try {
            doLock();
            succeed = true;
        } finally {
            if(!succeed) {
                this.lock.unlock();
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        //
        this.lock.lockInterruptibly();
        if(this.lock.getHoldCount() > 1) return;
        
        //
        boolean succeed = false;
        try {
            doLockInterruptibly();
            succeed = true;
        } finally {
            if(!succeed) {
                this.lock.unlock();
            }
        }
    }
    
    @Override
    public boolean tryLock() {
        //
        if(!this.lock.tryLock()) return false;
        if(this.lock.getHoldCount() > 1) return true;
        
        //
        boolean succeed = false;
        try {
            succeed = doTryLock();
        } finally {
            if(!succeed) {
                this.lock.unlock();
            }
        }
        return succeed;
    }

    @Override
    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        //
        final long mark = System.nanoTime();
        if(!this.lock.tryLock(timeout, unit)) return false;
        if(this.lock.getHoldCount() > 1) return true;
        
        //
        boolean succeed = false;
        try {
            timeout = TimeUnit.NANOSECONDS.convert(timeout, unit) - (System.nanoTime() - mark);
            if(timeout >= 0) {
                succeed = doTryLock(timeout, TimeUnit.NANOSECONDS);
            }
        } finally {
            if(!succeed) {
                this.lock.unlock();
            }
        }
        return succeed;
    }

    @Override
    public void unlock() {
        //
        if(!this.lock.isHeldByCurrentThread()) return;
        if(this.lock.getHoldCount() > 1) return;
        
        //
        try {
            doUnlock();
        } finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException();
    }
}