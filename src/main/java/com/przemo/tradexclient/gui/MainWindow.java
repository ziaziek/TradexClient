/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.tradexclient.gui;

import com.jidesoft.swing.JideMenu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
/**
 *
 * @author Przemo
 */
public class MainWindow extends JFrame {
    
    JideMenu mainMenu = null;
    JPanel mainPanel = null;

    
    public MainWindow(){
        super();
        this.setPreferredSize(new Dimension(800,300));
        setLayout(new BorderLayout());
        setBackground(Color.yellow);
        this.setTitle("Tradex Client");
        buildMenu();
        mainPanel();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    final void mainPanel(){
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.red);
        mainPanel.setPreferredSize(new Dimension(150, 150));
        this.add(mainPanel, BorderLayout.WEST);
    }
    
    final void buildMenu() {
        JMenuBar cmBar = new JMenuBar();
        MenuBuilder mb = new MenuBuilder(cmBar);
        mb.build();
        this.setJMenuBar(mb.getMenuProduced());
    }
}
