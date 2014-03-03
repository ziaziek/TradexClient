/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.tradexclient.gui;

import com.jidesoft.swing.JideMenu;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author Przemo
 */
public class MenuBuilder {

    public static final String FILE="File";
    public static final String EQUITIERS = "Equities";
    public static final String DATA="Data";
    public static final String TRADE = "Trade";
    public static final String WORKSPACE = "Workspace";
    public static final String HELP="Help";
    public static final String EXIT="Exit";
    public static final String CONNECT="Connect";
    public static final String DISCONNECT = "Disconnect";
    public static final String SHOW_AVAILABLE = "Show Available";
    public static final String RELOAD_AVAILABLE = "Reload Available";
    public static final String OPEN_CHART_WINDOW = "Open Equity Chart Window";
    public static final String DATA_DOWNLOAD = "Download data";
    public static final String SERVER_SETTINGS = "Server Settings";
    public static final String PLACE_ORDER = "Place Order";
    public static final String REMOVE_ORDER = "Remove Order";
    public static final String REMOVE_ALL_ORDERS = "Remove All Orders";
    public static final String SET_ORDER_TRIGGER = "Set Order Trigger";
    public static final String STOP_ORDER_TRIGGER = "Stop Order Trigger";
    public static final String LOAD_WORKSPACE = "Load Workspace";
    public static final String SAVE_WORKSPACE = "Save Wortkspace";
    public static final String LOAD_DEFAULT_WORKSPACE = "Load Default Workspace";
    public static final String ABOUT_HELP = "About";
    public static final String INDEX_HELP = "Index";
    
    JMenuBar menuProduced=null;

    JMenuItem currentItem, currentMenu=null;
    
    ActionListener acListener = null;

    public ActionListener getAcListener() {
        return acListener;
    }

    public void setAcListener(ActionListener acListener) {
        this.acListener = acListener;
    }
    
    public JMenuBar getMenuProduced() {
        return menuProduced;
    }
   
    public MenuBuilder(JMenuBar bar) {
        if(bar!=null){
           menuProduced = bar; 
        } else {
            menuProduced = new JMenuBar();
        }      
    }
    
    public void build() throws Exception {
        if(acListener==null){
            throw new Exception("No listener defined!");
        }
        //File
        List<JideMenu> menus = new ArrayList<>();
        menus.add(new JideMenu(FILE));
        menus.get(0).setPopupMenuCustomizer(new JideMenu.PopupMenuCustomizer() {
            @Override
            public void customize(JPopupMenu menu) {
                menu.removeAll();
                menu.add(CONNECT).addActionListener(acListener);
                menu.add(DISCONNECT).addActionListener(acListener);
                menu.add(EXIT).addActionListener(acListener);
            }
        });
        //Equities
        menus.add(new JideMenu(EQUITIERS));
        menus.get(1).setPopupMenuCustomizer(new JideMenu.PopupMenuCustomizer() {
            @Override
            public void customize(JPopupMenu menu) {
                menu.removeAll();
                menu.add(SHOW_AVAILABLE).addActionListener(acListener);
                menu.add(RELOAD_AVAILABLE).addActionListener(acListener);
                menu.add(OPEN_CHART_WINDOW).addActionListener(acListener);
            }
        });
        //Data
        menus.add(new JideMenu(DATA));
        menus.get(2).setPopupMenuCustomizer(new JideMenu.PopupMenuCustomizer() {
            @Override
            public void customize(JPopupMenu menu) {
                menu.removeAll();
                menu.add(DATA_DOWNLOAD).addActionListener(acListener);
                menu.add(SERVER_SETTINGS).addActionListener(acListener);
            }
        });
        //Trade
        menus.add(new JideMenu(TRADE));
        menus.get(3).setPopupMenuCustomizer(new JideMenu.PopupMenuCustomizer() {
            @Override
            public void customize(JPopupMenu menu) {
                menu.removeAll();
                menu.add(PLACE_ORDER).addActionListener(acListener);
                menu.add(REMOVE_ORDER).addActionListener(acListener);
                menu.add(REMOVE_ALL_ORDERS).addActionListener(acListener);
                menu.add(SET_ORDER_TRIGGER).addActionListener(acListener);
                menu.add(STOP_ORDER_TRIGGER).addActionListener(acListener);
            }
        });
        //Workspace
        menus.add(new JideMenu(WORKSPACE));
        menus.get(4).setPopupMenuCustomizer(new JideMenu.PopupMenuCustomizer() {
            @Override
            public void customize(JPopupMenu menu) {
                menu.removeAll();
                menu.add(LOAD_WORKSPACE).addActionListener(acListener);
                menu.add(SAVE_WORKSPACE).addActionListener(acListener);
                menu.add(LOAD_DEFAULT_WORKSPACE).addActionListener(acListener);
            }
        });
        //Help
        menus.add(new JideMenu(HELP));
        menus.get(5).setPopupMenuCustomizer(new JideMenu.PopupMenuCustomizer() {
            @Override
            public void customize(JPopupMenu menu) {
                menu.removeAll();
                menu.add(ABOUT_HELP).addActionListener(acListener);
                menu.add(INDEX_HELP).addActionListener(acListener);
            }
        });
        for(JMenu m: menus){
            menuProduced.add(m);
        }
    }

}
