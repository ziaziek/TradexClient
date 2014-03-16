/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.tradexclient;

import com.przemo.tradexclient.interfaces.ILoginSensitive;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Przemo
 */
public class ConnectionHolder {
    static String sessionId= null;
    public static String SERVER_ADDR = "localhost"; //TODO: change to a property
    private static long interval = 0;
    private static final long default_interval = 2500;
    public static long getInterval() {
        return interval;
    }

    /**
     * Sets interval. This can be done only once and the interval cannot be re-set
     * @param interval 
     */
    public static void setInterval(long interval) {
        if(ConnectionHolder.interval<=0 ){
           ConnectionHolder.interval = interval; 
        }
    }
    
    private static List<ILoginSensitive> loginSensitives;
    
    private static Thread th = null;
    
    public static void startMonitoring(){
        if(interval<=0){
            setInterval(default_interval);
        }
        if(th==null || !th.isAlive()){
          th = new Thread(){
            @Override
            public void run(){
                while(sessionId!=null){
                  try {
                       notifyLoginSensitiveObjects(sessionId!=null); 
                       //System.out.println("Notified listeners at "+ (new Date()).toString());
                       sleep(interval);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ConnectionHolder.class.getName()).log(Level.SEVERE, null, ex);
                }  
                }
            }
        };  
          th.start();
        }  
    }
    
    public static void stopMonitoring(){
        if(th!=null && th.isAlive()){
            th.interrupt();
        }
    }
    
    public static String getSessionId() {
        return sessionId;
    }
    
    public static void setSessionId(String sessionId) {
        ConnectionHolder.sessionId = sessionId;
        notifyLoginSensitiveObjects(sessionId!=null);
        if(sessionId!=null){
            startMonitoring();
        } else {
            stopMonitoring();
        }
        
    }
    
    protected static List<ILoginSensitive> getLoginSensitives(){
        if(loginSensitives==null){
            loginSensitives=new ArrayList<>();
        }
        return loginSensitives;
    }
    
    public static void registerLoginSensitive(ILoginSensitive l){
        if(!getLoginSensitives().contains(l)){
            getLoginSensitives().add(l);
        }
    }
    
    public static boolean unregisterLoginSensitive(ILoginSensitive l){
        if(getLoginSensitives().contains(l)){
            getLoginSensitives().remove(l);
        }
        return getLoginSensitives().contains(l);
    }
    
    public static void notifyLoginSensitiveObjects(boolean login){
        for(ILoginSensitive l: getLoginSensitives()){
            if(l!=null){
               l.loginUpdate(login); 
            } 
        }
    }
}
