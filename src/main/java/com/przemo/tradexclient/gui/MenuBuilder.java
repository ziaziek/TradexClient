/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.tradexclient.gui;

import com.jidesoft.swing.JideMenu;
import java.awt.Color;
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

    JMenuBar menuProduced=null;

    JMenuItem currentItem, currentMenu=null;
    
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
    
    public void build() {
        //File
        List<JideMenu> menus = new ArrayList<>();
        menus.add(new JideMenu("File"));
        menus.get(0).setPopupMenuCustomizer(new JideMenu.PopupMenuCustomizer() {
            @Override
            public void customize(JPopupMenu menu) {
                menu.removeAll();
                menu.add("Connect");
                menu.add("Disconnect");
                menu.add("Exit");
            }
        });
        //Equities
        menus.add(new JideMenu("Equities"));
        menus.get(1).setPopupMenuCustomizer(new JideMenu.PopupMenuCustomizer() {
            @Override
            public void customize(JPopupMenu menu) {
                menu.removeAll();
                menu.add("Show Available");
                menu.add("Reload Available");
                menu.add("Open Equity Chart Window");
            }
        });
        //Data
        menus.add(new JideMenu("Data"));
        menus.get(2).setPopupMenuCustomizer(new JideMenu.PopupMenuCustomizer() {
            @Override
            public void customize(JPopupMenu menu) {
                menu.removeAll();
                menu.add("Download");
                menu.add("Server Settings");
                menu.add("Open Equity Chart Window");
            }
        });
        //Trade
        menus.add(new JideMenu("Trade"));
        menus.get(3).setPopupMenuCustomizer(new JideMenu.PopupMenuCustomizer() {
            @Override
            public void customize(JPopupMenu menu) {
                menu.removeAll();
                menu.add("Place Order");
                menu.add("Remove Order");
                menu.add("Remove All Orders");
                menu.add("Set Order Trigger");
                menu.add("Stop Order Trigger");
            }
        });
        //Workspace
        menus.add(new JideMenu("Workspace"));
        menus.get(4).setPopupMenuCustomizer(new JideMenu.PopupMenuCustomizer() {
            @Override
            public void customize(JPopupMenu menu) {
                menu.removeAll();
                menu.add("Load Workspace");
                menu.add("Save Workspace");
                menu.add("Load Default Workspace");
            }
        });
        //Help
        menus.add(new JideMenu("Help"));
        menus.get(5).setPopupMenuCustomizer(new JideMenu.PopupMenuCustomizer() {
            @Override
            public void customize(JPopupMenu menu) {
                menu.removeAll();
                menu.add("About");
                menu.add("Index");
            }
        });
        for(JMenu m: menus){
            menuProduced.add(m);
        }
    }

}
