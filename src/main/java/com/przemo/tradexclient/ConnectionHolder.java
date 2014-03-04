/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.tradexclient;

import com.przemo.tradexclient.interfaces.ILoginSensitive;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Przemo
 */
public class ConnectionHolder {
    static String sessionId= null;
    public static String SERVER_ADDR = "localhost"; //TODO: change to a property
    private static List<ILoginSensitive> loginSensitives;
    
    public static String getSessionId() {
        return sessionId;
    }
    
    public static void setSessionId(String sessionId) {
        ConnectionHolder.sessionId = sessionId;
        ConnectionHolder.notifyLoginSensitiveObjects(sessionId!=null);
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
               l.loginChanged(login); 
            } 
        }
    }
}
