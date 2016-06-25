package com.cheyooh.tools.lock;

import java.util.concurrent.locks.Lock;  

public interface DistributedLock extends Lock {  
      
    Listener getListener();  
      
    void setListener(Listener listener);  
      
    /** 
     *  
     */  
    interface Listener {  
          
        void onAbort(DistributedLock lock, Exception e);  
    }  
}  