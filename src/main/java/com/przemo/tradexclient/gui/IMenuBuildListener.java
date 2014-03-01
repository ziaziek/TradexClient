/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.tradexclient.gui;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author Przemo
 */
public interface IMenuBuildListener {
    JMenu addMenu(String name);
    JMenuItem addMenuItem(String menu);
    void addActionCommand( String actionString);
}
