/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.tradexclient.remote;

import com.przemo.tradex.interfaces.ILoginController;
import com.przemo.tradexclient.App;
import com.przemo.tradexclient.ConnectionHolder;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Przemo
 */
public class RemoteAction {
    private static Registry RemoteActionRegistry = null;
    
    public static void initRegistry(String host, int port){
        if(RemoteActionRegistry==null){
            try {
                RemoteActionRegistry = LocateRegistry.getRegistry(host, port);
            } catch (RemoteException ex) {
                Logger.getLogger(RemoteAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static Registry getRegistry() throws RemoteActionInitializationException{
        if(RemoteActionRegistry==null){
            throw new RemoteActionInitializationException();
        }
        return RemoteActionRegistry;
    }

    public static boolean logIn(String user, String pwd) throws RemoteActionInitializationException, RemoteException, NotBoundException, UnknownHostException{
        ILoginController ctrl = (ILoginController) getRegistry().lookup(ILoginController.loginController_ID);
        ConnectionHolder.setSessionId(ctrl.loginRequest(user, pwd, InetAddress.getLocalHost().getHostAddress()));
        return ConnectionHolder.getSessionId()!=null;
    }
    
    public static boolean logOut() throws RemoteActionInitializationException, RemoteException, NotBoundException{
        ILoginController ctrl = (ILoginController) getRegistry().lookup(ILoginController.loginController_ID);
        boolean v = ctrl.logoutRequest(ConnectionHolder.getSessionId());
        if(v){
            ConnectionHolder.setSessionId(null);
        }
        return v;
    }
}