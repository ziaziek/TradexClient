/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.tradexclient.gui;

import com.jidesoft.swing.JideMenu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
/**
 *
 * @author Przemo
 */
public class MainWindow extends JFrame {
    
    JideMenu mainMenu = null;
    JPanel mainPanel = null;
    
    private Map<String, Map<String, String>> menuItemsCommands;
    
    public MainWindow(){
        super();
        this.setPreferredSize(new Dimension(800,300));
        setLayout(new BorderLayout());
        setBackground(Color.yellow);
        this.setTitle("Tradex Client");
        buildMenu();
        mainPanel();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    final void mainPanel(){
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.red);
        mainPanel.setPreferredSize(new Dimension(150, 150));
        this.add(mainPanel, BorderLayout.WEST);
    }
    
    final void buildMenu() {
        JMenuBar cmBar = new JMenuBar();
        JideMenu fileMenu = new JideMenu("File");
        fileMenu.setPopupMenuCustomizer(new JideMenu.PopupMenuCustomizer() {
            @Override
            public void customize(JPopupMenu menu) {
                menu.removeAll();
                menu.add("Connect");
                menu.add("Disconnect");
                menu.add("Exit");
            }
        });
        
        cmBar.add(fileMenu);
        this.setJMenuBar(cmBar);
        fileMenu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
}
