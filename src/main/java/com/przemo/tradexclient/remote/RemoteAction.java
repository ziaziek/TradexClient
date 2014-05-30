/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.tradexclient.remote;

import com.przemo.tradex.data.Equities;
import com.przemo.tradex.data.OrderTypes;
import com.przemo.tradex.data.Orders;
import com.przemo.tradex.data.UserSessions;
import com.przemo.tradex.interfaces.IInfoController;
import com.przemo.tradex.interfaces.ILoginController;
import com.przemo.tradex.interfaces.IOrdersController;
import com.przemo.tradexclient.App;
import com.przemo.tradexclient.ConnectionHolder;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Przemo
 */
public class RemoteAction {
    
    public static final int ORDER_BUY = 1;
    public static final int ORDER_SELL = 2;
    
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
    
    public static Set<Equities> getEquities(String sessId) throws RemoteActionInitializationException, RemoteException, NotBoundException{
        IInfoController ctrl = (IInfoController) getRegistry().lookup(IInfoController.infoController_ID);
        Set<Equities> ret = ctrl.requestAvailableInstruments(sessId);
        return ret;
    }
    
    public static List<UserSessions> getSessionsHistory(String sid) throws RemoteActionInitializationException, RemoteException, NotBoundException{
        IInfoController ctrl = (IInfoController) getRegistry().lookup(IInfoController.infoController_ID); 
        Calendar c = Calendar.getInstance(); 
        c.set(1900, 00, 01);
        List<UserSessions> ret = ctrl.requestActivity(c.getTime(), new Date(), sid); 
        return     ret;
    }
    
    public static Equities getEquityQuotation(Equities eq) throws RemoteActionInitializationException, RemoteException, NotBoundException{
        IInfoController ctrl = (IInfoController) getRegistry().lookup(IInfoController.infoController_ID);
        Equities ret = ctrl.requestQuotation(new Date(), eq, ConnectionHolder.getSessionId()); 
        return ret;
    }
    
    public static boolean placeOrder(Equities eq, double amount, double price, int type) throws RemoteActionInitializationException, RemoteException, NotBoundException {
        //orders will be currently placed at the specific expiry term of 1 day
        IInfoController ctrl = (IInfoController) getRegistry().lookup(IInfoController.infoController_ID);
        List<OrderTypes> otypes = ctrl.requestAvailableOrderTypes();
        if (otypes != null && !otypes.isEmpty()) {
            IOrdersController oc = (IOrdersController) getRegistry().lookup(IOrdersController.ordersController_ID);
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, 1);
            return oc.placeOrder(eq, amount, c.getTime(), otypes.get(type-1), ConnectionHolder.getSessionId()) > 0L;
        } else {
            return false;
        }
    }
    
    public static boolean removeOrder(long id) throws RemoteActionInitializationException, RemoteException, NotBoundException {
        IOrdersController oc = (IOrdersController) getRegistry().lookup(IOrdersController.ordersController_ID);
        return (oc != null) && oc.removeOrder(id, ConnectionHolder.getSessionId()) > -1;
    }
     
    public static List<Orders> getActiveUserOrders() throws RemoteActionInitializationException, RemoteException, NotBoundException{
        List<Orders> ret = null;
        IInfoController ctrl = (IInfoController) getRegistry().lookup(IInfoController.infoController_ID);
        ret = ctrl.requestOrders(ConnectionHolder.getSessionId(), true);
        return ret;
    }
}
