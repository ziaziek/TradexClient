/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.tradexclient.remote;

/**
 *
 * @author Przemo
 */
public class RemoteActionInitializationException extends Exception {
    
    final String message = "Registry has not been initialized for the Actions!";
    
    @Override
    public String getMessage(){
        return message;
    }
}
