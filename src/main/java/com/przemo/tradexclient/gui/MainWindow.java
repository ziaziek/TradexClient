/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.tradexclient.gui;

import com.jidesoft.swing.JideMenu;
import com.przemo.tradexclient.App;
import com.przemo.tradexclient.interfaces.ILoginSensitive;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.Border;
/**
 *
 * @author Przemo
 */
public class MainWindow extends JFrame implements ActionListener, WindowListener, ILoginSensitive {
    
    JideMenu mainMenu = null;
    JPanel mainPanel = null;
    JPanel statusBar=null;
    JLabel loggedInAs=null;
    
    public MainWindow(){
        super();
        this.setPreferredSize(new Dimension(800,300));
        setLayout(new BorderLayout());
        setBackground(Color.yellow);
        this.setTitle("Tradex Client");
        try{
            buildMenu();    
        } catch(Exception ex){
            System.out.println(ex.getMessage());
            this.dispose();
        }       
        mainPanel();
        buildStatusPanel();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.addWindowListener(this);
    }
    
    final void mainPanel(){
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.red);
        mainPanel.setPreferredSize(new Dimension(150, 150));
        this.add(mainPanel, BorderLayout.WEST);
    }
    
    final void buildMenu() throws Exception {
        JMenuBar cmBar = new JMenuBar();
        MenuBuilder mb = new MenuBuilder(cmBar);
        mb.setAcListener(this);
        mb.build();
        this.setJMenuBar(mb.getMenuProduced());
    }

    final void buildStatusPanel(){
        statusBar = new JPanel(new BorderLayout());
        loggedInAs = new JLabel("NOT logged in");
        statusBar.add(loggedInAs, BorderLayout.EAST);
        statusBar.setBorder(BorderFactory.createEtchedBorder());
        add(statusBar, BorderLayout.SOUTH);
    }
    
    private void handleClosing(){
        System.out.println("Window gets closed");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String currentOption=null;
        if(e.getSource() instanceof JMenuItem){
            currentOption = ((JMenuItem)e.getSource()).getText();
        } else {
            return;
        }
        switch(currentOption){
            case MenuBuilder.EXIT:
                handleClosing();
                 this.dispose();
            break;
            case MenuBuilder.CONNECT:
                LoginForm lf = new LoginForm();
                lf.setVisible(true);
                lf.addLoginSensitive(this);
                Dimension dScreen = Toolkit.getDefaultToolkit().getScreenSize();
                lf.setLocation((int)((dScreen.getWidth()- lf.getWidth())/2) , (int)((dScreen.getHeight() - lf.getHeight())/2));
                break;
            case MenuBuilder.DISCONNECT:
                
                break;
            case MenuBuilder.SHOW_AVAILABLE:
                
                break;
            case MenuBuilder.RELOAD_AVAILABLE:
                
                break;
            case MenuBuilder.OPEN_CHART_WINDOW:
                
                break;
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        handleClosing();
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void loginChanged(boolean isLoggedin) {
        if(isLoggedin){
            loggedInAs.setText("Logged In");
        } else {
            loggedInAs.setText("NOT logged in");
        }
    }

}
